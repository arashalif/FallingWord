package com.arashaghelifar.fallingword.presentation.navigation

import com.arashaghelifar.fallingword.presentation.navigation.ARGS.GAME_SCREEN_ARG
object Screens {
    const val START_SCREEN = "startScreen"

    const val GAME_SCREEN = "gameScreen/?$GAME_SCREEN_ARG={$GAME_SCREEN_ARG}"
}
object ARGS {
    const val GAME_SCREEN_ARG = "isNewGame"
    const val GAME_SCREEN_ARG_PLACEHOLDER = "isNewGame"
}