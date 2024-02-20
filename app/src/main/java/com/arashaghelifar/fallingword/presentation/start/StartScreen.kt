package com.arashaghelifar.fallingword.presentation.start

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arashaghelifar.fallingword.R
import com.arashaghelifar.fallingword.common.BaseResponse
import com.arashaghelifar.fallingword.presentation.model.StatsString
import com.arashaghelifar.fallingword.presentation.model.StatsUIModel
import com.arashaghelifar.fallingword.ui.components.StatsCard

@Composable
fun StartScreen(
    feedBacks: BaseResponse<StatsUIModel>,
    previousGame: BaseResponse<StatsUIModel>,
    onPlayGameClick: (Boolean) -> Unit,
    onStatsRetry: () -> Unit,
    onPreviousGameRetry: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        StatsCard(
            statsUIModel = feedBacks,
            onRetry = onStatsRetry,
            onButtonClick = {}
        )

        Spacer(modifier = Modifier.height(16.dp))
        StatsCard(
            statsUIModel = previousGame,
            onRetry = onPreviousGameRetry,
            onButtonClick = { onPlayGameClick(false) }
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(4.dp),
            onClick = {
                onPlayGameClick(true)
            }
        ) {
            Text(
                text = "New Game",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    val stats = listOf(
        StatsString(
            keyHolder = R.string.startScreen_totalGame,
            keyValue = "10"
        ),
        StatsString(
            keyHolder = R.string.startScreen_totalGame,
            keyValue = "10"
        ),
        StatsString(
            keyHolder = R.string.startScreen_totalGame,
            keyValue = "10"
        ),
    )

    val statsUiModel = StatsUIModel(
        title = R.string.startScreen_feedback,
        statsStrings = stats
    )

    StartScreen(
        feedBacks = BaseResponse.Success(statsUiModel),
        previousGame = BaseResponse.Success(statsUiModel),
        onPlayGameClick = {},
        onStatsRetry = {},
        onPreviousGameRetry = {}
    )
}