package com.example.tictactoe.presentation

import androidx.lifecycle.ViewModel
import com.example.tictactoe.domain.TicTacToeHandler
import com.example.tictactoe.presentation.mvi.TicTacToeUIEvent
import com.example.tictactoe.presentation.mvi.TicTacToeState
import com.example.tictactoe.utils.StatusGame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TicTacToeViewModel(
    private val ticTacToe: TicTacToeHandler
) : ViewModel() {
    private val _ticTacToeState = MutableStateFlow(TicTacToeState())
    val uiState = _ticTacToeState.asStateFlow()

    fun onEvent(event: TicTacToeUIEvent) {
        when (event) {
            is TicTacToeUIEvent.DrawBoard -> updateBoard()
            is TicTacToeUIEvent.MakeMove -> makeMove(move = event.move)
            is TicTacToeUIEvent.RestartGame -> restartGame()
            is TicTacToeUIEvent.UpdateText -> updateText(text = event.text)
            is TicTacToeUIEvent.UpdateVisibilityText -> updateVisibilityText(isVisible = event.isVisible)
        }
    }

    private fun updateBoard() {
        _ticTacToeState.update { it.copy(board = ticTacToe.getBoard()) }
    }

    private fun makeMove(move: String) {
        when (val statusGame = ticTacToe.makeMove(move)) {
            is StatusGame.Progress -> {
                _ticTacToeState.update {
                    it.copy(
                        currentTurn = statusGame.turn,
                        error = null
                    )
                }
            }

            is StatusGame.Draw -> {
                _ticTacToeState.update {
                    it.copy(
                        isDraw = true,
                        isFinished = true
                    )
                }
            }

            is StatusGame.Win -> {
                _ticTacToeState.update {
                    it.copy(
                        winner = statusGame.turn,
                        isFinished = true,
                        error = null
                    )
                }
            }

            is StatusGame.Error -> {
                _ticTacToeState.update { it.copy(error = statusGame.message) }
            }
        }
    }

    private fun restartGame() {
        ticTacToe.restartGame()

        _ticTacToeState.update {
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

    private fun updateText(text: String) {
        _ticTacToeState.update { it.copy(textState = text) }
    }

    private fun updateVisibilityText(isVisible: Boolean) {
        _ticTacToeState.update { it.copy(isVisibleText = isVisible) }
    }
}