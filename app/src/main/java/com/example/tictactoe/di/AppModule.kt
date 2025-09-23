package com.example.tictactoe.di

import com.example.tictactoe.presentation.TicTacToeViewModel
import org.koin.dsl.module

val appModule = module {
    single { TicTacToeViewModel(ticTacToe = get()) }
}