package com.example.noteapp3.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.noteapp3.model.Notes
import com.example.noteapp3.repository.RepositoryRoom
import com.example.noteapp3.roomdb.NotesDao
import com.example.noteapp3.roomdb.NotesDatabase
import kotlinx.coroutines.launch

class AddNoteViewModel(application: Application) : AndroidViewModel(application) {

    val repositoryRoom : RepositoryRoom

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).notesDao()
        repositoryRoom = RepositoryRoom(dao)
    }


    fun addNotes(notes : Notes) {
        repositoryRoom.insertNotes(notes)

    }
    fun deleteNotes(notes : Notes)  {
        repositoryRoom.deleteNotes(notes)
    }
    fun updateNotes(notes: Notes)  {
        repositoryRoom.updateNotes(notes)
    }
    fun getData() : LiveData<List<Notes>> = repositoryRoom.getData()

    val noteLiveData = MutableLiveData<Notes>()

    val dao = NotesDatabase.getDatabaseInstance(application).notesDao()
    fun getNote(getnote : Int){
        repositoryRoom.getNote(getnote)

        val notes = dao.getNote(getnote)
        noteLiveData.value = notes


    }
}