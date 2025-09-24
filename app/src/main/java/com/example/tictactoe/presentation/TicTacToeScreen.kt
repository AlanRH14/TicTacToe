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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.tictactoe.R
import com.example.tictactoe.presentation.mvi.TicTacToeUIEvent
import com.example.tictactoe.presentation.widgets.Board
import com.example.tictactoe.ui.theme.SpacerPadding
import org.koin.androidx.compose.koinViewModel

@Composable
fun TicTacToeScreen(
    modifier: Modifier = Modifier,
    viewModel: TicTacToeViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(event = TicTacToeUIEvent.DrawBoard)
    }

    Column(
        modifier =
            modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Board(uiState) {
            viewModel.onEvent(event = TicTacToeUIEvent.MakeMove(move = it))
        }

        Spacer(modifier = Modifier.height(SpacerPadding))

        var isVisibleStateText by remember { mutableStateOf(false) }

        when {
            !uiState.isFinished && uiState.error == null -> {
                viewModel.onEvent(event = TicTacToeUIEvent.UpdateMove(text = "Current Player: ${uiState.currentTurn}"))
                isVisibleStateText = true
            }

            uiState.error != null -> {
                viewModel.onEvent(event = TicTacToeUIEvent.UpdateMove(text = "${uiState.error}"))
                isVisibleStateText = true
            }

            uiState.winner != null -> {
                viewModel.onEvent(event = TicTacToeUIEvent.UpdateMove(text = "Winner Player: ${uiState.winner}"))
                isVisibleStateText = true
            }

            uiState.isDraw -> {
                viewModel.onEvent(event = TicTacToeUIEvent.UpdateMove(text = "Draw"))
                isVisibleStateText = true
            }

            else -> {
                isVisibleStateText = false
            }
        }

        AnimatedVisibility(visible = isVisibleStateText) {
            Text(text = uiState.textState)
        }

        Spacer(modifier = Modifier.height(SpacerPadding))

        if (uiState.isFinished) {
            Button(
                onClick = { viewModel.onEvent(event = TicTacToeUIEvent.RestartGame) }
            ) {
                Text(text = stringResource(R.string.restart_button))
            }
        }
    }
}