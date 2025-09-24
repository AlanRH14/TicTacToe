package com.example.tictactoe.presentation.mvi

sealed interface TicTacToeUIEvent {
    data object DrawBoard : TicTacToeUIEvent
}