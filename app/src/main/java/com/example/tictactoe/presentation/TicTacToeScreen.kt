package com.example.tictactoe.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactoe.presentation.widgets.Board
import com.example.tictactoe.ui.theme.SpacerPadding

@Composable
fun TicTacToeScreen(
    modifier: Modifier = Modifier,
    viewModel: TicTacToeViewModel = viewModel()
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

        AnimatedVisibility(visible = !uiState.isFinished && uiState.error == null) {
            Text("Current Player: ${uiState.currentTurn}")
        }

        AnimatedVisibility(visible = uiState.error != null) {
            Text(text = "${uiState.error}")
        }

        AnimatedVisibility(visible = uiState.winner != null) {
            Text("Winner Player: ${uiState.winner}")
        }

        AnimatedVisibility(visible = uiState.isDraw) {
            Text("Draw")
        }
    }
}