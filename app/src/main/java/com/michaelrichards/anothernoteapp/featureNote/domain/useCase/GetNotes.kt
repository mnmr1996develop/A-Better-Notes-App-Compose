package com.michaelrichards.anothernoteapp.featureNote.domain.useCase

import com.michaelrichards.anothernoteapp.featureNote.domain.model.Note
import com.michaelrichards.anothernoteapp.featureNote.domain.repository.NoteRepository
import com.michaelrichards.anothernoteapp.featureNote.domain.util.NoteOrder
import com.michaelrichards.anothernoteapp.featureNote.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(
    private val repository: NoteRepository
) {

    operator fun invoke(noteOrder: NoteOrder = NoteOrder.EditedDate(OrderType.Descending)): Flow<List<Note>> {
        return repository.getNotes().map { notes ->
            when(noteOrder.orderType){
                is OrderType.Ascending -> {
                    when(noteOrder){
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.CreatedDate -> notes.sortedBy { it.dateCreated }
                        is NoteOrder.EditedDate -> notes.sortedBy { it.lastEdited }
                        is NoteOrder.Color -> notes.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when(noteOrder){
                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.CreatedDate -> notes.sortedByDescending { it.dateCreated }
                        is NoteOrder.EditedDate -> notes.sortedByDescending { it.lastEdited }
                        is NoteOrder.Color -> notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }

}