package com.michaelrichards.anothernoteapp.featureNote.data.dataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.michaelrichards.anothernoteapp.featureNote.domain.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }

}