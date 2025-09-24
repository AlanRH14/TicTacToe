package com.example.tictactoe.presentation.mvi

sealed interface TicTacToeUIEvent {
    data object DrawBoard : TicTacToeUIEvent
    data class MakeMove(val move: String) : TicTacToeUIEvent
    data object RestartGame : TicTacToeUIEvent
    data class UpdateText(val text: String) : TicTacToeUIEvent
    data class UpdateVisibilityText(val isVisible: Boolean): TicTacToeUIEvent
}