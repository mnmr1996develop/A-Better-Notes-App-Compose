package com.michaelrichards.anothernoteapp.featureNote.domain.useCase

import com.michaelrichards.anothernoteapp.featureNote.domain.model.Note
import com.michaelrichards.anothernoteapp.featureNote.domain.repository.NoteRepository

class DeleteNote(private val  repository: NoteRepository) {

    suspend operator fun invoke(note: Note){
        repository.deleteNote(note)
    }
}