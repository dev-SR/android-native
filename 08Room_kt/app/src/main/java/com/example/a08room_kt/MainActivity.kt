package com.example.a08room_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.a08room_kt.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var vb: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)
//      vb.
        val repository = NoteRepository(applicationContext)
        CoroutineScope(Dispatchers.IO).launch {
//            //insert
            for (i in 0..5) {
                repository.insertNote(
                    Note(
                        "Note: ${Random.nextInt(1000)}",
                        "Description",
                        priority = Random.nextInt(5)
                    )
                )
            }

            //read
            withContext(Dispatchers.Main) {
                for (i in NoteRepository(applicationContext).getNotes()) {
                    Log.d("LOG_DB", i.title)
                }
            }
//            repository.clearNotes()

        }
    }

}