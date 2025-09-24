package com.example.tictactoe.presentation

import androidx.lifecycle.ViewModel
import com.example.tictactoe.domain.TicTacToeHandler
import com.example.tictactoe.presentation.mvi.TicTacToeUIEvent
import com.example.tictactoe.utils.StatusGame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TicTacToeViewModel(
    private val ticTacToe: TicTacToeHandler
) : ViewModel() {
    private val _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: TicTacToeUIEvent) {
        when (event) {
            is TicTacToeUIEvent.DrawBoard -> updateBoard()
            is TicTacToeUIEvent.MakeMove -> makeMove(move = event.move)
            is TicTacToeUIEvent.RestartGame -> restartGame()
        }
    }

    private fun updateBoard() {
        _uiState.update { it.copy(board = ticTacToe.getBoard()) }
    }

    private fun makeMove(move: String) {
        when (val statusGame = ticTacToe.makeMove(move)) {
            is StatusGame.Progress -> {
                _uiState.update {
                    it.copy(
                        currentTurn = statusGame.turn,
                        error = null
                    )
                }
            }

            is StatusGame.Draw -> {
                _uiState.update {
                    it.copy(
                        isDraw = true,
                        isFinished = true
                    )
                }
            }

            is StatusGame.Win -> {
                _uiState.update {
                    it.copy(
                        winner = statusGame.turn,
                        isFinished = true,
                        error = null
                    )
                }
            }

            is StatusGame.Error -> {
                _uiState.update { it.copy(error = statusGame.message) }
            }
        }
    }

    private fun restartGame() {
        ticTacToe.restartGame()

        _uiState.update {
            it.copy(
                board = ticTacToe.getBoard(),
                currentTurn = 'X',
                error = null,
                winner = null,
                isFinished = false,
                isDraw = false
            )
        }
    }

    private fun updateMove(move: String) {
        _uiState.update { it.copy(move = move) }
    }
}