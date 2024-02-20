package com.arashaghelifar.fallingword.presentation.model

import androidx.annotation.StringRes

data class StatsUIModel(
    @StringRes val title: Int,
    val statsStrings: List<StatsString> = listOf(),
    @StringRes val buttonTitle: Int? = null,
)

data class StatsString(
    val keyValue: String,
    @StringRes val keyHolder: Int,
)