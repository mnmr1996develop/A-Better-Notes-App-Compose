package com.michaelrichards.anothernoteapp.featureNote.presentation.notesScreen.compoonents

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.michaelrichards.anothernoteapp.featureNote.domain.util.NoteOrder
import com.michaelrichards.anothernoteapp.featureNote.domain.util.OrderType


@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.CreatedDate(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            NotesRadioButton(text = "Title", selected = noteOrder is NoteOrder.Title) {
                onOrderChange(NoteOrder.Title(noteOrder.orderType))
            }
            NotesRadioButton(text = "Last Edited", selected = noteOrder is NoteOrder.EditedDate) {
                onOrderChange(NoteOrder.Title(noteOrder.orderType))
            }
            NotesRadioButton(text = "Created", selected = noteOrder is NoteOrder.CreatedDate) {
                onOrderChange(NoteOrder.Title(noteOrder.orderType))
            }
            NotesRadioButton(text = "Color", selected = noteOrder is NoteOrder.Color) {
                onOrderChange(NoteOrder.Title(noteOrder.orderType))
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            NotesRadioButton(
                text = "Ascending",
                selected = noteOrder.orderType is OrderType.Ascending
            ) {
                onOrderChange(noteOrder.copy(OrderType.Ascending))
            }
            NotesRadioButton(
                text = "Descending",
                selected = noteOrder.orderType is OrderType.Descending
            ) {
                onOrderChange(noteOrder.copy(OrderType.Descending))
            }
        }

    }

}