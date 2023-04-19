package com.michaelrichards.anothernoteapp.featureNote.data.dataSource

import androidx.room.Database
import com.michaelrichards.anothernoteapp.featureNote.domain.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase {
    abstract val noteDao: NoteDao

}