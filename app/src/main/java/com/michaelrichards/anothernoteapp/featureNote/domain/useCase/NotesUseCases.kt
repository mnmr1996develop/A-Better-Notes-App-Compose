package com.michaelrichards.anothernoteapp.featureNote.domain.useCase

data class NotesUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getNote: GetNote
)
