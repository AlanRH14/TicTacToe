package com.example.tictactoe.presentation

import androidx.lifecycle.ViewModel
import com.example.tictactoe.domain.TicTacToeHandler
import com.example.utils.StatusGame
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TicTacToeViewModel @Inject constructor(
    private val ticTacToe: TicTacToeHandler
) : ViewModel() {
    private val _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

    init {
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
}