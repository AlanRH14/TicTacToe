package com.example.tictactoe.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tictactoe.R
import com.example.tictactoe.presentation.widgets.Board
import com.example.tictactoe.ui.theme.SpacerPadding
import org.koin.androidx.compose.koinViewModel

@Composable
fun TicTacToeScreen(
    modifier: Modifier = Modifier,
    viewModel: TicTacToeViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier =
            modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Board(uiState) {
            viewModel.makeMove(it)
        }

        Spacer(modifier = Modifier.height(SpacerPadding))

        var textState by remember { mutableStateOf("") }

        var isVisibleStateText by remember { mutableStateOf(false) }

        when {
            !uiState.isFinished && uiState.error == null -> {
                textState = "Current Player: ${uiState.currentTurn}"
                isVisibleStateText = true
            }

            uiState.error != null -> {
                textState = "${uiState.error}"
                isVisibleStateText = true
            }

            uiState.winner != null -> {
                textState = "Winner Player: ${uiState.winner}"
                isVisibleStateText = true
            }

            uiState.isDraw -> {
                textState = "Draw"
                isVisibleStateText = true
            }

            else -> {
                isVisibleStateText = false
            }
        }

        AnimatedVisibility(visible = isVisibleStateText) {
            Text(textState)
        }

        Spacer(modifier = Modifier.height(SpacerPadding))

        if (uiState.isFinished) {
            Button(
                onClick = { viewModel.restartGame() }
            ) {
                Text(text = stringResource(R.string.restart_button))
            }
        }
    }
}