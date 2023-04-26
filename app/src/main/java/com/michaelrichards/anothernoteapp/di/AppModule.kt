package com.michaelrichards.anothernoteapp.di

import android.app.Application
import androidx.room.Room
import com.michaelrichards.anothernoteapp.featureNote.data.dataSource.NoteDatabase
import com.michaelrichards.anothernoteapp.featureNote.data.repository.NoteRepositoryImp
import com.michaelrichards.anothernoteapp.featureNote.domain.repository.NoteRepository
import com.michaelrichards.anothernoteapp.featureNote.domain.useCase.AddNote
import com.michaelrichards.anothernoteapp.featureNote.domain.useCase.DeleteNote
import com.michaelrichards.anothernoteapp.featureNote.domain.useCase.GetNote
import com.michaelrichards.anothernoteapp.featureNote.domain.useCase.GetNotes
import com.michaelrichards.anothernoteapp.featureNote.domain.useCase.NotesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase{
        return Room.databaseBuilder(app, NoteDatabase::class.java, NoteDatabase.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db : NoteDatabase): NoteRepository{
        return NoteRepositoryImp(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NotesUseCases{
        return NotesUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }
}