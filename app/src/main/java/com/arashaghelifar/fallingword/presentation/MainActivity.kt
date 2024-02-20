package com.arashaghelifar.fallingword.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arashaghelifar.fallingword.presentation.game.GameScreen
import com.arashaghelifar.fallingword.presentation.game.GameViewModel
import com.arashaghelifar.fallingword.presentation.navigation.ARGS.GAME_SCREEN_ARG
import com.arashaghelifar.fallingword.presentation.navigation.Screens
import com.arashaghelifar.fallingword.presentation.start.StartScreen
import com.arashaghelifar.fallingword.presentation.start.StartViewModel
import com.arashaghelifar.fallingword.ui.theme.FallingWordTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FallingWordTheme {
                AppContent()
            }
        }
    }
    @Composable
    fun AppContent() {
        val navController = rememberNavController()
        NavHost(navController = navController,
            startDestination = Screens.START_SCREEN,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        200, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(200, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        200, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(200, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            },
            popEnterTransition = {
                fadeIn(
                    animationSpec = tween(
                        200, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(200, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            popExitTransition = {
                fadeOut(
                    animationSpec = tween(
                        200, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(200, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            }) {

            composable(route = Screens.START_SCREEN) {
                val viewModel = hiltViewModel<StartViewModel>()
                val feedBacks by viewModel.feedBackUiState.collectAsState()
                val previousGame by viewModel.previousGameStatsUiState.collectAsState()
                StartScreen(
                    feedBacks = feedBacks,
                    previousGame = previousGame,
                    onPlayGameClick = {
                        navController.navigate(
                            Screens.GAME_SCREEN.replace("{$GAME_SCREEN_ARG}", it.toString())
                        )
                    },
                    onStatsRetry = {},
                    onPreviousGameRetry = {})
            }
            composable(
                route = Screens.GAME_SCREEN,
                arguments = listOf(navArgument(name = GAME_SCREEN_ARG) {
                    type = NavType.BoolType
                    defaultValue = true
                })
            ) { backStackEntry ->

                val viewModel = hiltViewModel<GameViewModel>()

                // DisposableEffect to listen for activity lifecycle events
                DisposableEffect(key1 = this) {
                    val activity = this@MainActivity
                    val observer =
                        object : DefaultLifecycleObserver {
                            override fun onStop(owner: LifecycleOwner) {
                                super.onStop(owner)
                                // Execute the callback when the app goes to the background
                                viewModel.saveGame()
                                navController.popBackStack()
                            }
                        }

                    activity.lifecycle.addObserver(observer)
                    onDispose {
                        activity.lifecycle.removeObserver(observer)
                    }
                }

                BackHandler {
                    // Handle back button press here
                    viewModel.saveGame()
                    navController.popBackStack()
                }

                LaunchedEffect(true) {
                    viewModel.fetchGame(
                        backStackEntry.arguments?.getBoolean(GAME_SCREEN_ARG) ?: true
                    )
                }

                val activeQuestion by viewModel.activeQuestion.collectAsState()
                val gameStats by viewModel.gameStats.collectAsState()
                val countDownTimer by viewModel.countDownTimer.collectAsState()
                val isCorrect by viewModel.isCorrectAnimation.collectAsState()
                GameScreen(
                    gameStats = gameStats,
                    question = activeQuestion,
                    countDownTimer = countDownTimer,
                    isCorrect = isCorrect,
                    onActionButtonClick = { question, correct ->
                        viewModel.checkAnswerOfQuestion(
                            question,
                            correct
                        )
                    },
                    onRetryClick = {
                        viewModel.fetchGame(
                            backStackEntry.arguments?.getBoolean(GAME_SCREEN_ARG) ?: true
                        )
                    },
                    onFinishedClick = {
                        viewModel.saveGame()
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
