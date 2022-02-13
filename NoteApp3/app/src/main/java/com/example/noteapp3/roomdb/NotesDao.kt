package com.example.noteapp3.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.noteapp3.model.Notes

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes")
    fun getNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertNotes(notes: Notes)

    @Delete
     fun deleteNotes(notes: Notes)

    @Update
     fun updateNotes(notes: Notes)

    @Query("SELECT * FROM notes WHERE id = :notesId")
     fun getNote(notesId : Int) : Notes
}