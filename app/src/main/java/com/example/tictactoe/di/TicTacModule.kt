package com.example.tictactoe.di

import com.example.tictactoe.domain.TicTacToeHandler
import com.example.tictactoe.domain.model.TicTacToeData
import org.koin.dsl.module

val ticTacModule = module {
    single { TicTacToeData() }

    single { TicTacToeHandler(ticTacToeData = get()) }
}