package com.example.noteapp3.repository

import androidx.lifecycle.LiveData
import com.example.noteapp3.model.Notes
import com.example.noteapp3.roomdb.NotesDao

class RepositoryRoom(val dao : NotesDao) {

    fun getData(): LiveData<List<Notes>>{
        return dao.getNotes()
    }
     fun insertNotes(notes: Notes){
        dao.insertNotes(notes)
    }

     fun deleteNotes(notes: Notes){
        dao.deleteNotes(notes)

    }

     fun updateNotes(notes: Notes) {
        dao.updateNotes(notes)

    }

    fun getNote(noteId : Int){
        dao.getNote(noteId)
    }

}