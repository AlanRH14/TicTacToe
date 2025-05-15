package com.example.tictactoe.utils

sealed class StatusGame {
    data class Progress(val turn: Char) : StatusGame()
    data object Draw : StatusGame()
    data class Win(val turn: Char) : StatusGame()
    data class Error(val message: String) : StatusGame()
}