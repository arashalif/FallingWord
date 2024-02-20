package com.arashaghelifar.fallingword.presentation.game.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.arashaghelifar.fallingword.domain.model.Question

@Composable
fun GameBox(
    question: Question,
    onActionButtonClick: (Question, Boolean) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        FallingWord(suggestion = question.suggestionWord, timeToAnswer = question.timeToAnswerInSec)

        // Text First

        BasicText(
            text = question.questionWord,
            style = TextStyle(
                fontSize = 20.sp,
                color = Color.Black
            ),
            modifier = Modifier.align(Alignment.TopCenter)
        )

        // Row pinned to bottom
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ActionButton(text = "Incorrect", color = Color.Red) {
                onActionButtonClick(question, false)
            }

            Spacer(modifier = Modifier.weight(1f))

            ActionButton(text = "Correct", color = Color.Green) {
                onActionButtonClick(question, true)
            }
        }
    }
}

@Composable
fun FallingWord(suggestion: String, timeToAnswer: Int) {
    val animatable = remember { Animatable(0f) }
    val localDensity = LocalDensity.current
    var columnHeightDp by remember {
        mutableStateOf(0.dp)
    }

    LaunchedEffect(suggestion) {
        animatable.snapTo(0f)
        animatable.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = timeToAnswer * 1000,
                easing = LinearEasing
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                columnHeightDp = with(localDensity) { coordinates.size.height.toDp() }
            }
            .padding(vertical = 16.dp)
            .offset(y = animatable.value * columnHeightDp) // Adjust the value for your screen size
    ) {
        BasicText(
            modifier = Modifier.align(Alignment.TopCenter),
            text = suggestion,
            style = TextStyle(
                fontSize = 20.sp,
                color = Color.Black
            )
        )
    }
}

@Composable
fun ActionButton(
    text: String,
    color: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(120.dp),
        shape = RoundedCornerShape(10),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Preview
@Composable
fun GameBoxPreview() {
    val question = Question(
        questionWord = "Hi",
        suggestionWord = "Hallo",
        hashedAnswerWord = "hello",
        isCorrectAnswered = false,
        isPlayed = false,
        score = null,
    )

    GameBox(question = question){_,_ ->}

}