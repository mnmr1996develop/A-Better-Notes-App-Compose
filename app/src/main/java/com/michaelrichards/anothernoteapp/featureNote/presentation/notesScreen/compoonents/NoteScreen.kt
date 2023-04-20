package com.michaelrichards.anothernoteapp.featureNote.presentation.notesScreen.compoonents

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.michaelrichards.anothernoteapp.featureNote.presentation.notesScreen.NotesEvent
import com.michaelrichards.anothernoteapp.featureNote.presentation.notesScreen.NotesViewModel
import kotlinx.coroutines.launch

@Composable
fun NoteScreen(navController: NavController, viewModel: NotesViewModel = hiltViewModel()) {

    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
            }
        }, scaffoldState = scaffoldState
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Your Note", style = MaterialTheme.typography.h4)
                IconButton(onClick = { viewModel.onEvent(NotesEvent.ToggleOrderSection) }) {
                    Icon(imageVector = Icons.Default.Sort, contentDescription = "Sort")
                }
            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    noteOrder = state.noteOrder,
                    onOrderChange = {
                        viewModel.onEvent(NotesEvent.Order(it))
                    })
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()){
                    items(state.notes){note ->
                        NoteItem(note = note, modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }) {
                            viewModel.onEvent(NotesEvent.DeleteNote(note))
                            scope.launch { 
                                val res = scaffoldState.snackbarHostState.showSnackbar("Note deleted", actionLabel = "Undo")
                                
                                if (res == SnackbarResult.ActionPerformed){
                                    viewModel.onEvent(NotesEvent.RestoreNote)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}