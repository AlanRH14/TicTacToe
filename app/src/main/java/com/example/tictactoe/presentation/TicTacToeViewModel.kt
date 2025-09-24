package com.example.tictactoe.presentation

import androidx.lifecycle.ViewModel
import com.example.tictactoe.domain.TicTacToeHandler
import com.example.tictactoe.presentation.mvi.TicTacToeUIEvent
import com.example.tictactoe.utils.StatusGame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TicTacToeViewModel(
    private val ticTacToe: TicTacToeHandler
) : ViewModel() {
    private val _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

    private fun onEvent(event: TicTacToeUIEvent) {
        when (event) {
            is TicTacToeUIEvent.DrawBoard -> updateBoard()
            is TicTacToeUIEvent.MakeMove -> makeMove(move = event.move)
        }
    }

    private fun updateBoard() {
        _uiState.value = _uiState.value.copy(board = ticTacToe.getBoard())
    }

    fun makeMove(move: String) {
        when (val statusGame = ticTacToe.makeMove(move)) {
            is StatusGame.Progress -> {
                _uiState.value = _uiState.value.copy(currentTurn = statusGame.turn, error = null)
            }

            is StatusGame.Draw -> {
                _uiState.value = _uiState.value.copy(
                    isDraw = true,
                    isFinished = true
                )
            }

            is StatusGame.Win -> {
                _uiState.value = _uiState.value.copy(
                    winner = statusGame.turn,
                    isFinished = true,
                    error = null
                )
            }

            is StatusGame.Error -> {
                _uiState.value = _uiState.value.copy(error = statusGame.message)
            }
        }
    }

    fun restartGame() {
        ticTacToe.restartGame()

        _uiState.value = _uiState.value.copy(
            board = ticTacToe.getBoard(),
            currentTurn = 'X',
            error = null,
            winner = null,
            isFinished = false,
            isDraw = false
        )
    }
}