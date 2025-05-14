package com.example.tictactoe.presentation

import androidx.lifecycle.ViewModel
import com.TicTacToe
import com.example.utils.StatusGame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TicTacToeViewModel : ViewModel() {
    private val ticTacToe = TicTacToe()
    private val _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = _uiState.value.copy(board = ticTacToe.getBoard())
    }

    fun makeMove(move: String) {
        when (val statusGame = ticTacToe.makMove(move)) {
            is StatusGame.Progress -> {
                _uiState.value = _uiState.value.copy(currentTurn = statusGame.turn)
            }

            is StatusGame.Draw -> {
                _uiState.value = _uiState.value.copy(isDraw = true, isFinished = false)
            }

            is StatusGame.Win -> {
                _uiState.value = _uiState.value.copy(winner = statusGame.turn)
            }

            is StatusGame.Error -> {
                _uiState.value = _uiState.value.copy(error = statusGame.message)
            }
        }
    }

    private fun updateBoard() {
        _uiState.value = _uiState.value.copy(board = emptyList())
        _uiState.value = _uiState.value.copy(board = ticTacToe.getBoard())
    }
}