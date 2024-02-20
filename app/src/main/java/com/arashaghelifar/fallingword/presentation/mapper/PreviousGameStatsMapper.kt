package com.arashaghelifar.fallingword.presentation.mapper

import com.arashaghelifar.fallingword.R
import com.arashaghelifar.fallingword.common.Extension.calculatePercentOf
import com.arashaghelifar.fallingword.domain.model.Stats
import com.arashaghelifar.fallingword.presentation.model.StatsString
import com.arashaghelifar.fallingword.presentation.model.StatsUIModel

/**
 * Mapper object responsible for mapping domain model [Stats] to UI model [StatsUIModel].
 */
object PreviousGameStatsMapper {

    /**
     * Maps the domain model [Stats] to the UI model [StatsUIModel].
     *
     * @param stats The domain model [Stats] to be mapped.
     * @return The UI model [StatsUIModel] mapped from the domain model [Stats].
     */
    fun map(stats: Stats): StatsUIModel {

        val statsStrings = mutableListOf<StatsString>().apply {
            //           Questions played Stats
            add(
                StatsString(
                    keyValue = "${stats.questionPlayedCount}/${stats.questionCount}",
                    keyHolder = R.string.startScreen_QuestionPlayed
                )
            )
            //           Correct Answer Stats
            add(
                StatsString(
                    keyValue = stats.questionPlayedCount.calculatePercentOf(stats.correctCount),
                    keyHolder = R.string.startScreen_CorrectAnswer
                )
            )
            //           Game Progress Stats
            add(
                StatsString(
                    keyValue = stats.questionCount.calculatePercentOf(stats.questionPlayedCount),
                    keyHolder = R.string.startScreen_gameProgress
                )
            )
            //           Total Score Stats
            add(
                StatsString(
                    keyValue = stats.scoreCount.toString(),
                    keyHolder = R.string.startScreen_score
                )
            )
        }

        return StatsUIModel(
            title = R.string.startScreen_previousGame,
            statsStrings = statsStrings,
            buttonTitle = if(stats.remainingCount > 0) R.string.startScreen_resumeGame else null
        )
    }
}