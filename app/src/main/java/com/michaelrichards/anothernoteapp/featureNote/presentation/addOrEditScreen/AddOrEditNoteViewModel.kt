package com.michaelrichards.anothernoteapp.featureNote.presentation.addOrEditScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michaelrichards.anothernoteapp.featureNote.domain.model.Note
import com.michaelrichards.anothernoteapp.featureNote.domain.useCase.NotesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddOrEditNoteViewMode @Inject constructor(private val notesUseCases: NotesUseCases, savedStateHandle: SavedStateHandle) :
    ViewModel() {

    private val _noteTitle = mutableStateOf(NoteTextFieldState(hint = "Enter Title..."))
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteBody = mutableStateOf(NoteTextFieldState(hint = "Enter Note Body"))
    val noteBody: State<NoteTextFieldState> = _noteBody

    private val _noteColor = mutableStateOf(Note.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1){
                viewModelScope.launch {
                    notesUseCases.getNote(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(text = note.title, isHintVisible = false)
                        _noteBody.value = noteBody.value.copy(text = note.content, isHintVisible = false)
                        _noteColor.value = note.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.ChangeBodyFocus -> _noteBody.value = noteBody.value.copy(isHintVisible = !event.focusState.isFocused && noteBody.value.text.isBlank())
            is AddEditNoteEvent.ChangeColor ->  _noteColor.value = event.color
            is AddEditNoteEvent.ChangeTitleFocus -> _noteTitle.value =
                noteTitle.value.copy(isHintVisible = !event.focusState.isFocused && noteTitle.value.text.isBlank())
            is AddEditNoteEvent.EnteredBody -> _noteBody.value = noteTitle.value.copy()
            is AddEditNoteEvent.EnteredTitle -> _noteTitle.value = noteTitle.value.copy()
            AddEditNoteEvent.SaveNote -> viewModelScope.launch {
                try {
                    notesUseCases.addNote(
                        Note(
                            title = noteTitle.value.text,
                            content = noteBody.value.text,
                            dateCreated = System.currentTimeMillis(),
                            lastEdited = System.currentTimeMillis(),
                            color = noteColor.value,
                            id = currentNoteId
                        )
                    )
                    _eventFlow.emit(UiEvent.SaveNote)
                }catch (e: Exception){
                    _eventFlow.emit(UiEvent.ShowSnackbar(
                        message = e.message ?: "Could Not Save Note"
                    ))
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveNote : UiEvent()
    }
}