package com.example.tictactoe

import android.app.Application
import com.example.tictactoe.di.ticTacModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication

@HiltAndroidApp
class TicTacToeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        koinApplication {
            androidContext(this@TicTacToeApplication)
            modules(ticTacModule)
        }
    }
}