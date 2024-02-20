package com.arashaghelifar.fallingword.presentation.game

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arashaghelifar.fallingword.R
import com.arashaghelifar.fallingword.common.BaseResponse
import com.arashaghelifar.fallingword.domain.model.Question
import com.arashaghelifar.fallingword.presentation.game.components.GameBox
import com.arashaghelifar.fallingword.presentation.model.StatsUIModel
import com.arashaghelifar.fallingword.ui.components.StatsCard

@Composable
fun GameScreen(
    gameStats: BaseResponse<StatsUIModel>,
    question: Question?,
    countDownTimer: Int?,
    isCorrect: Boolean?,
    onActionButtonClick: (Question, Boolean) -> Unit,
    onRetryClick:()->Unit,
    onFinishedClick:()->Unit,
) {
    val color = remember { Animatable(Color.White) }

    isCorrect?.let {
        LaunchedEffect(question) {
            color.animateTo(if (it) Color.Green else Color.Red, animationSpec = tween(500))
            color.animateTo(Color.White, animationSpec = tween(500))
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color.value)
            .padding(16.dp)
    ) {

        StatsCard(
            statsUIModel = gameStats,
            onRetry = onRetryClick,
            onButtonClick = onFinishedClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        countDownTimer?.let {
            // CountDown Timer

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.gameScreen_remainingTime, it.toString()),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        question?.let {
            GameBox(
                question = question,
                onActionButtonClick = onActionButtonClick
            )
        }
    }
}