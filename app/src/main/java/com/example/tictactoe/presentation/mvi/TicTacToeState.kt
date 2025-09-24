package com.example.tictactoe.presentation.mvi

data class TicTacToeState(
    val board: List<MutableList<Char>> = emptyList(),
    val textState: String = "",
    val isVisibleText: Boolean = false,
    val currentTurn: Char = 'X',
    val error: String? = null,
    val winner: Char? = null,
    val isFinished: Boolean = false,
    val isDraw: Boolean = false
)