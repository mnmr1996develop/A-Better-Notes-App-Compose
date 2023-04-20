package com.michaelrichards.anothernoteapp.featureNote.domain.useCase

import com.michaelrichards.anothernoteapp.featureNote.domain.model.Note
import com.michaelrichards.anothernoteapp.featureNote.domain.repository.NoteRepository

class AddNote (private val  repository: NoteRepository) {

    @Throws(java.lang.Exception::class)
    suspend operator fun invoke(note: Note){
        if (note.title.isBlank()){
            throw Exception("Title Cannot be Empty")
        }
        if (note.content.isBlank()){
            throw Exception("Title Cannot be Empty")
        }
        repository.insertNote(note)
    }
}