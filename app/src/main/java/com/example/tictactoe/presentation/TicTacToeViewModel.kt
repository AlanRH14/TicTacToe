package com.example.tictactoe.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tictactoe.domain.TicTacToeHandler
import com.example.utils.StatusGame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TicTacToeViewModel : ViewModel() {
    private val ticTacToe = TicTacToeHandler()
    private val _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = _uiState.value.copy(board = ticTacToe.getBoard())
    }

    fun makeMove(move: String) {
        when (val statusGame = ticTacToe.makeMove(move)) {
            is StatusGame.Progress -> {
                Log.d("LordMiau", "Progress")
                _uiState.value = _uiState.value.copy(currentTurn = statusGame.turn, error = null)
            }

            is StatusGame.Draw -> {
                Log.d("LordMiau", "Draw")
                _uiState.value = _uiState.value.copy(
                    isDraw = true,
                    isFinished = true
                )
            }

            is StatusGame.Win -> {
                Log.d("LordMiau", "Win")
                _uiState.value = _uiState.value.copy(
                    winner = statusGame.turn,
                    isFinished = true,
                    error = null
                )
            }

            is StatusGame.Error -> {
                Log.d("LordMiau", "Error")
                _uiState.value = _uiState.value.copy(error = statusGame.message)
            }
        }
    }
}