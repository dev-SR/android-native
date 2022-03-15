package com.example.a08room_kt

import android.content.Context

class NoteRepository(context: Context) {
    private val db = NoteDatabase.getInstance(context).noteDao()

    suspend fun insertNote(note: Note) {
        db.insert(note)
    }
    suspend fun getNotes(): List<Note> {
        return db.getAllNotes()
    }
    suspend fun clearNotes(){
        db.deleteAllNotes()
    }
    suspend fun deleteNote(note: Note){
        db.delete(note)
    }
}