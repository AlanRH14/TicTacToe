package com.example.tictactoe.di

import com.example.tictactoe.domain.TicTacToeHandler
import com.example.tictactoe.domain.model.TicTacToeData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TicTacToeModule {
}