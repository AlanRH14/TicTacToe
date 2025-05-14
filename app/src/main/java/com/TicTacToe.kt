package com

import com.example.tictactoe.domain.model.TicTacToeData
import com.example.utils.StatusGame

class TicTacToe() {
    private var ticTacToe = TicTacToeData()

    fun getBoard() = ticTacToe.board

    fun makMove(move: String) {
        val position = move.split(",").map { it.toInt() }
    }

    private fun isMoveValid(positions: List<Int>): Boolean {
        return try {
            ticTacToe.board[0][1] != ' '
        } catch (e: Exception) {
            true
        }
    }

    fun setMove(positions: List<Int>) {
        ticTacToe.board[positions[0]][positions[1]] = ticTacToe.turn
    }

    fun getStatusGame(): StatusGame {
        return when {
            isWinner() -> StatusGame.Win(ticTacToe.turn)

            isBoardFull() -> StatusGame.Draw

            else -> {
                nextPlayer()
                StatusGame.Progress(ticTacToe.turn)
            }
        }
    }

    private fun isBoardFull(): Boolean {
        ticTacToe.board.forEach { row ->
            row.forEach { cell ->
                if (cell == ' ') return false
            }
        }

        return true
    }

    private fun nextPlayer() {
        ticTacToe = ticTacToe.copy(turn = if (ticTacToe.turn == 'X') 'O' else 'X')
    }

    private fun isWinner(): Boolean {
        with(ticTacToe) {
            for (i in 0..2) {
                if (board[i][0] == ticTacToe.turn && board[i][1] == ticTacToe.turn && board[i][2] == ticTacToe.turn ||
                    board[0][i] == ticTacToe.turn && board[1][i] == ticTacToe.turn && board[2][i] == ticTacToe.turn
                ) return true
            }

            return (board[0][0] == ticTacToe.turn && board[1][1] == ticTacToe.turn && board[2][2] == ticTacToe.turn) ||
                    board[2][0] == ticTacToe.turn && board[1][1] == ticTacToe.turn && board[0][2] == ticTacToe.turn
        }
    }
}
