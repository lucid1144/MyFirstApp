package com.example.mynote

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class NoteViewModel : ViewModel() {
    var notes by mutableStateOf(emptyList<Note>())
    private set

    var searchQuery by mutableStateOf("")

    var selectedNote by mutableStateOf<Note?>(null)
    private set

    fun selectedNote(note: Note) {
        selectedNote = note
    }


    fun addNote(note: Note) {
        notes = notes + note
    }

    fun deleteNote(note: Note) {
        notes = notes - note
    }

    fun editNote(note: Note) {
        notes = notes.map {
            if (it.id == note.id) note else it
        }
    }

    val filteredNotes: List<Note>
        get() {
            if (searchQuery.isBlank()) {
                return notes
            } else {
                return notes.filter {
                    it.title.contains(searchQuery, ignoreCase = true) ||
                            it.content.contains(searchQuery, ignoreCase = true)
                }
            }
        }
}