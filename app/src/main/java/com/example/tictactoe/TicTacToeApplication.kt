package com.example.tictactoe

import android.app.Application
import com.example.tictactoe.di.ticTacModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication

class TicTacToeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TicTacToeApplication)
            modules(ticTacModule)
        }
    }
}