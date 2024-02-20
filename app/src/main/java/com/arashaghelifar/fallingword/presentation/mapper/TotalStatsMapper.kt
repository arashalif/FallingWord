package com.arashaghelifar.fallingword.presentation.mapper

import com.arashaghelifar.fallingword.R
import com.arashaghelifar.fallingword.common.Extension.calculatePercentOf
import com.arashaghelifar.fallingword.domain.model.Stats
import com.arashaghelifar.fallingword.presentation.model.StatsString
import com.arashaghelifar.fallingword.presentation.model.StatsUIModel

/**
 * Mapper object responsible for mapping domain model [Stats] to UI model [StatsUIModel] for total statistics.
 */
object TotalStatsMapper {

    /**
     * Maps the domain model [Stats] to the UI model [StatsUIModel] for total statistics.
     *
     * @param stats The domain model [Stats] to be mapped.
     * @return The UI model [StatsUIModel] mapped from the domain model [Stats] for total statistics.
     */
    fun map(stats: Stats): StatsUIModel {

        val statsStrings = mutableListOf<StatsString>().apply {
            //           Total Question Stats
            add(
                StatsString(
                    keyValue = stats.questionCount.toString(),
                    keyHolder = R.string.startScreen_totalQuestion
                )
            )
            //           Total Correct Percent Stats
            add(
                StatsString(
                    keyValue = stats.questionCount.calculatePercentOf(stats.correctCount),
                    keyHolder = R.string.startScreen_totalCorrect
                )
            )
            //           Total Question Stats
            add(
                StatsString(
                    keyValue = stats.gameCount.toString(),
                    keyHolder = R.string.startScreen_totalGame
                )
            )
            //           Total Score Stats
            add(
                StatsString(
                    keyValue = stats.scoreCount.toString(),
                    keyHolder = R.string.startScreen_totalScore
                )
            )
        }

        return StatsUIModel(
            title = R.string.startScreen_feedback,
            statsStrings = statsStrings,
            buttonTitle = null
        )
    }
}