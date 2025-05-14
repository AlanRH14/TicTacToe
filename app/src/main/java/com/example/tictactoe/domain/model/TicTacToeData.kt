package com.example.tictactoe.domain.model

data class TicTacToeData(
    val board: MutableList<MutableList<Char>> = mutableListOf(
        mutableListOf(' ', ' ', ' '),
        mutableListOf(' ', ' ', ' '),
        mutableListOf(' ', ' ', ' '),
    ),
    val turn: Char = 'X'
)
