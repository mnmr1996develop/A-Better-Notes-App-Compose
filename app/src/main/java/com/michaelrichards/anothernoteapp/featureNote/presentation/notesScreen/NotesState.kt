package com.michaelrichards.anothernoteapp.featureNote.presentation.notesScreen

import com.michaelrichards.anothernoteapp.featureNote.domain.model.Note
import com.michaelrichards.anothernoteapp.featureNote.domain.util.NoteOrder
import com.michaelrichards.anothernoteapp.featureNote.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.CreatedDate(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
