package com.arashaghelifar.fallingword.domain.usecase

import com.arashaghelifar.fallingword.common.BaseResponse
import com.arashaghelifar.fallingword.domain.Constants.TotalQuestion
import com.arashaghelifar.fallingword.domain.mapper.RandomWordsMapper
import com.arashaghelifar.fallingword.domain.mapper.WordsToQuestionsMapper
import com.arashaghelifar.fallingword.domain.model.Game
import com.arashaghelifar.fallingword.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Use case responsible for initializing a new game session.
 *
 * @property gameRepository Repository for accessing word data.
 */
class InitialGameUseCase @Inject constructor(
    private val gameRepository: GameRepository,
) {

    /**
     * Initializes a new game session with a specified number of questions.
     *
     * @param totalQuestions Total number of questions to be included in the game. Defaults to [TotalQuestion].
     * @return A flow emitting the base response containing the game session.
     */
    suspend operator fun invoke(totalQuestions: Int = TotalQuestion): Flow<BaseResponse<Game>> {
        return gameRepository.fetchWords().map { response ->
            when (response) {
                is BaseResponse.Error -> BaseResponse.Error(response.error)
                BaseResponse.Loading -> BaseResponse.Loading
                is BaseResponse.Success -> {
                    val words = response.data
                    val shuffledWords = RandomWordsMapper.map(words, totalQuestions)
                    val questions = WordsToQuestionsMapper.map(words, shuffledWords)
                    val game = Game(
                        questions = questions
                    )
                    BaseResponse.Success(data = game)
                }
            }
        }
    }
}