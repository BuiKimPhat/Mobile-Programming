package com.example.mynote.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynote.data.NoteRepository
import com.example.mynote.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository)
    : ViewModel(){
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
                .collect {
                    listOfNote ->
                        if (listOfNote.isNullOrEmpty()){
                            Log.d("DEBUG1","Empty or Null")
                        } else {
                            _noteList.value = listOfNote
                        }
                }
        }

        fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note) }
        fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }
        fun removeNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }
    }
}