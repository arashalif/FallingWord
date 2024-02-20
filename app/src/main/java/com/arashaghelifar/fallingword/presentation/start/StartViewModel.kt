package com.arashaghelifar.fallingword.presentation.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arashaghelifar.fallingword.common.BaseResponse
import com.arashaghelifar.fallingword.domain.usecase.PreviousGameStatsUseCase
import com.arashaghelifar.fallingword.domain.usecase.TotalStatsUseCase
import com.arashaghelifar.fallingword.presentation.mapper.PreviousGameStatsMapper
import com.arashaghelifar.fallingword.presentation.mapper.TotalStatsMapper
import com.arashaghelifar.fallingword.presentation.model.StatsUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val totalStatsUseCase: TotalStatsUseCase,
    private val previousGameStatsUseCase: PreviousGameStatsUseCase,
) : ViewModel() {

    private val _feedBackUiState = MutableStateFlow<BaseResponse<StatsUIModel>>(BaseResponse.Loading)
    val feedBackUiState: StateFlow<BaseResponse<StatsUIModel>> = _feedBackUiState

    private val _previousGameStatsUiState =
        MutableStateFlow<BaseResponse<StatsUIModel>>(BaseResponse.Loading)
    val previousGameStatsUiState: StateFlow<BaseResponse<StatsUIModel>> = _previousGameStatsUiState

    init {
        observeTotalStats()
        observePreviousGame()
    }

    private fun observeTotalStats() {
        viewModelScope.launch {
            totalStatsUseCase().collect {
                _feedBackUiState.emit(
                    when (it) {
                        is BaseResponse.Error -> BaseResponse.Error(error = it.error)
                        BaseResponse.Loading -> BaseResponse.Loading
                        is BaseResponse.Success -> BaseResponse.Success(TotalStatsMapper.map(it.data))
                    }
                )
            }
        }
    }

    private fun observePreviousGame() {
        viewModelScope.launch {
            previousGameStatsUseCase().collect {
                _previousGameStatsUiState.emit(
                    when (it) {
                        is BaseResponse.Error -> BaseResponse.Error(error = it.error)
                        BaseResponse.Loading -> BaseResponse.Loading
                        is BaseResponse.Success -> BaseResponse.Success(PreviousGameStatsMapper.map(it.data))
                    }
                )
            }
        }
    }
}