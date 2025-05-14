package com.example.tictactoe.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactoe.presentation.widgets.Board

@Composable
fun TicTacToeScreen(viewModel: TicTacToeViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Board(uiState) {

    }
}