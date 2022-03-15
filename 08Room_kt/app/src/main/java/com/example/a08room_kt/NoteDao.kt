package com.example.a08room_kt

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("delete from note_table")
    suspend fun deleteAllNotes()

    @Query("select * from note_table order by priority desc")
    suspend fun getAllNotes(): List<Note>
}