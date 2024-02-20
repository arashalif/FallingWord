package com.arashaghelifar.fallingword.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arashaghelifar.fallingword.R
import com.arashaghelifar.fallingword.common.BaseResponse
import com.arashaghelifar.fallingword.presentation.model.StatsString
import com.arashaghelifar.fallingword.presentation.model.StatsUIModel

@Composable
fun StatsCard(
    statsUIModel: BaseResponse<StatsUIModel>,
    onRetry: () -> Unit,
    onButtonClick: () -> Unit,
) {
    when (statsUIModel) {
        is BaseResponse.Error -> RetryCard(message = statsUIModel.error, onRetry = onRetry)
        BaseResponse.Loading -> LoadingCard()
        is BaseResponse.Success -> {
            val stats = statsUIModel.data

            Card(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {

                    Text(
                        text = stringResource(id = stats.title),
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                    ) {
                        items(stats.statsStrings) { statsString ->
                            StatsCell(
                                statsString = statsString,
                                modifier = Modifier
                            )
                        }
                    }

                    stats.buttonTitle?.let {
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { onButtonClick() }) {
                            Text(stringResource(id = it))
                        }
                    }

                }
            }
        }
    }
}


@Composable
fun StatsCell(
    statsString: StatsString,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        style = MaterialTheme.typography.bodySmall,
        text = stringResource(
            id = statsString.keyHolder,
            statsString.keyValue
        )
    )
}

@Composable
fun LoadingCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(64.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    }
}

@Composable
fun RetryCard(message: String? = null, onRetry: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                message ?: stringResource(R.string.startScreen_failedToLoadData),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onRetry) {
                Text(stringResource(R.string.startScreen_retry))
            }
        }
    }
}

@Preview
@Composable
fun StatsCardPreview() {

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

    StatsCard(
        statsUIModel = BaseResponse.Success(statsUiModel),
        onRetry = {},
        onButtonClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun LoadingCardPreview() {
    LoadingCard()
}

@Preview(showBackground = true)
@Composable
fun RetryCardPreview() {
    RetryCard(onRetry = {})
}
