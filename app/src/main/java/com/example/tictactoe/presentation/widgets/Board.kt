package com.example.tictactoe.presentation.widgets

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tictactoe.presentation.UIState

@Composable
fun Board(
    uiState: UIState,
    onClickCell: (String) -> Unit
) {
    LazyColumn {
        itemsIndexed(uiState.board) { indexRow, row ->
            LazyRow {
                itemsIndexed(row) { indexCell, cell ->
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .border(1.dp, MaterialTheme.colorScheme.onSurface)
                            .clickable {
                                if (!uiState.isFinished) {
                                    onClickCell("$indexRow,$indexCell")
                                }
                            }
                    )
                }
            }
        }
    }
}