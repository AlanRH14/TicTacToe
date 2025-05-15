package com.example.utils

import com.example.tictactoe.domain.TicTacToeHandler
import java.util.Scanner

private var isGameFinished = false
lateinit var ticTacToe: TicTacToeHandler

fun main() {
    val scanner = Scanner(System.`in`)

    displayBoard(ticTacToe.getBoard())
    println("Player X: ")

    while (!isGameFinished) {
        val move = scanner.nextLine()
        val statusGame = ticTacToe.makeMove(move)

        displayBoard(ticTacToe.getBoard())
        statusGame.validateGame()
    }
}

private fun displayBoard(board: MutableList<MutableList<Char>>) {
    println("-------------")
    board.forEach { row ->
        row.forEach { cell ->
            print("| $cell")
        }
        println("|")
    }
    println("-------------")
}

private fun StatusGame.validateGame() {
    when (this) {
        is StatusGame.Draw -> {
            println("Game finished - > Draw")
            isGameFinished = true
        }

        is StatusGame.Error -> {
            println(this.message)
        }

        is StatusGame.Progress -> println("Player $turn: ")

        is StatusGame.Win -> {
            isGameFinished = true
            println("Won: $turn")
        }
    }
}