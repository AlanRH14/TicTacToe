package com.example.tictactoe.domain

import com.example.tictactoe.domain.model.TicTacToeData
import com.example.tictactoe.utils.StatusGame
import javax.inject.Inject

class TicTacToeHandler(
    private var ticTacToeData: TicTacToeData
) {

    fun getBoard() = ticTacToeData.board

    fun makeMove(move: String): StatusGame {
        val positions = move.split(",").map { it.toInt() }
        if (isMoveValid(positions))
            return StatusGame.Error(message = "Invalid move, try again")

        setMove(positions)

        return getStatusGame()
    }

    fun restartGame() {
        ticTacToeData = ticTacToeData.copy(
            board = mutableListOf(
                mutableListOf(' ', ' ', ' '),
                mutableListOf(' ', ' ', ' '),
                mutableListOf(' ', ' ', ' '),
            ),
            turn = 'X'
        )
    }

    private fun isMoveValid(positions: List<Int>): Boolean {
        return try {
            ticTacToeData.board[positions[0]][positions[1]] != ' '
        } catch (e: Exception) {
            true
        }
    }

    private fun setMove(positions: List<Int>) {
        ticTacToeData.board[positions[0]][positions[1]] = ticTacToeData.turn
    }

    private fun getStatusGame(): StatusGame {
        return when {
            isWinner() -> StatusGame.Win(ticTacToeData.turn)

            isBoardFull() -> StatusGame.Draw

            else -> {
                nextPlayer()
                StatusGame.Progress(ticTacToeData.turn)
            }
        }
    }

    private fun nextPlayer() {
        ticTacToeData = ticTacToeData.copy(turn = if (ticTacToeData.turn == 'X') 'O' else 'X')
    }

    private fun isWinner(): Boolean {
        with(ticTacToeData) {
            for (i in 0..2) {
                if (board[i][0] == ticTacToeData.turn && board[i][1] == ticTacToeData.turn && board[i][2] == ticTacToeData.turn ||
                    board[0][i] == ticTacToeData.turn && board[1][i] == ticTacToeData.turn && board[2][i] == ticTacToeData.turn
                ) return true
            }

            return (board[0][0] == ticTacToeData.turn && board[1][1] == ticTacToeData.turn && board[2][2] == ticTacToeData.turn) ||
                    board[2][0] == ticTacToeData.turn && board[1][1] == ticTacToeData.turn && board[0][2] == ticTacToeData.turn
        }
    }

    private fun isBoardFull(): Boolean {
        ticTacToeData.board.forEach { row ->
            row.forEach { cell ->
                if (cell == ' ') return false
            }
        }

        return true
    }
}
