package com.example.tictactoe.presentation

data class UIState(
    val board: List<MutableList<Char>> = emptyList(),
    val move: String = "",
    val currentTurn: Char = 'X',
    val error: String? = null,
    val winner: Char? = null,
    val isFinished: Boolean = false,
    val isDraw: Boolean = false
)