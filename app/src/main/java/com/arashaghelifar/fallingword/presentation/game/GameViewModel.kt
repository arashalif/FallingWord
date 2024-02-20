package com.arashaghelifar.fallingword.presentation.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arashaghelifar.fallingword.common.BaseResponse
import com.arashaghelifar.fallingword.common.Extension.encrypt
import com.arashaghelifar.fallingword.domain.mapper.GameToStatsMapper
import com.arashaghelifar.fallingword.domain.model.Game
import com.arashaghelifar.fallingword.domain.model.Question
import com.arashaghelifar.fallingword.domain.usecase.InitialGameUseCase
import com.arashaghelifar.fallingword.domain.usecase.PreviousGameUseCase
import com.arashaghelifar.fallingword.domain.usecase.SaveGameUseCase
import com.arashaghelifar.fallingword.domain.usecase.SaveTotalStatsUseCase
import com.arashaghelifar.fallingword.presentation.mapper.ActiveGameStatsMapper
import com.arashaghelifar.fallingword.presentation.model.StatsUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val initialGameUseCase: InitialGameUseCase,
    private val previousGameUseCase: PreviousGameUseCase,
    private val saveTotalStatsUseCase: SaveTotalStatsUseCase,
    private val saveGameUseCase: SaveGameUseCase,
) : ViewModel() {

    private val _gameUiState = MutableStateFlow<BaseResponse<Game>>(BaseResponse.Loading)
    private val gameUiState = _gameUiState

    val activeQuestion: StateFlow<Question?> = gameUiState.map {
        if (it is BaseResponse.Success) {
            it.data.questions.firstOrNull { question ->
                !question.isPlayed
            }
        } else {
            null
        }
    }.onEach { question ->
        question?.let {
            startCountdown(it.timeToAnswerInSec)
        }


    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val gameStats: StateFlow<BaseResponse<StatsUIModel>> = gameUiState.map {
        when (it) {
            is BaseResponse.Error -> BaseResponse.Error(it.error)
            BaseResponse.Loading -> BaseResponse.Loading
            is BaseResponse.Success -> {
                BaseResponse.Success(ActiveGameStatsMapper.map(GameToStatsMapper.map(it.data)))
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), BaseResponse.Loading)

    private val _countDownTimer = MutableStateFlow<Int?>(null)
    val countDownTimer: StateFlow<Int?> = _countDownTimer
    private var countdownJob: Job? = null

    private val _isCorrectAnimation = MutableStateFlow<Boolean?>(null)
    val isCorrectAnimation: StateFlow<Boolean?> = _isCorrectAnimation

    fun fetchGame(isNewGame: Boolean) {
        viewModelScope.launch {
            if (isNewGame) {
                initialGameUseCase().collect {
                    _gameUiState.emit(it)
                }
            } else {
                previousGameUseCase().collect {
                    _gameUiState.emit(it)
                }
            }
        }
    }

    private fun startCountdown(timeToAnswerInSec: Int) {
        countdownJob = viewModelScope.launch {
            var timeLeft = timeToAnswerInSec
            while (timeLeft > 0) {
                delay(1000)
                timeLeft--
                _countDownTimer.emit(timeLeft)
            }
            // Countdown finished
            if (isActive) {
                countdownFinished()
            }
        }
    }

    private fun countdownFinished() {
        nextQuestion(false)

    }

    private fun nextQuestion(isCorrect: Boolean) {
        viewModelScope.launch {
            _isCorrectAnimation.emit(isCorrect)
            stopTimer()
            gameUiState.value.run {
                if (this is BaseResponse.Success) {
                    val questions = this.data.questions.toMutableList()
                    val questionIndex = questions.indexOf(activeQuestion.value)
                    questions[questionIndex] = questions[questionIndex].copy(
                        isPlayed = true,
                        isCorrectAnswered = isCorrect,
                        score = if (isCorrect) countDownTimer.value else 0
                    )
                    _gameUiState.emit(
                        BaseResponse.Success(Game(questions = questions))
                    )
                }
            }


        }
    }

    private fun stopTimer() {
        countdownJob?.cancel()
    }

    fun checkAnswerOfQuestion(question: Question, isCorrect: Boolean) {
        val isSuggestionCorrect = question.suggestionWord.encrypt() == question.hashedAnswerWord
        nextQuestion(isSuggestionCorrect == isCorrect)
    }

    fun saveGame() {
        viewModelScope.launch {
            when (val game = gameUiState.value) {
                is BaseResponse.Success -> {
                    if (game.data.questions.all { it.isPlayed }) {
                        saveTotalStatsUseCase(game.data)
                    } else {
                        saveGameUseCase(game.data)
                    }
                }

                else -> {
                }
            }
        }
    }
}