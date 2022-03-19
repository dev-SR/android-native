package com.example.a09firebase_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.a09firebase_kt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var vb: ActivityMainBinding
    private lateinit var viewModel: FireStoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)


        // ViewModel initialization:
        val factory = FireStoreViewModel.Factory(applicationContext, FirebaseRepository())
        viewModel = ViewModelProvider(this, factory).get(FireStoreViewModel::class.java)


        viewModel.getTodos().observe(this) {
            var value = "";

//            for (document in it) {
//                Log.d("FireStore", document.todoId + " => " + document.title)
//            }

            it.forEach { todo: Todo -> value += "\n${todo.title}" }
            Log.d("FireStore", value)

            vb.textView.text = value
        }
        vb.button.setOnClickListener {
            viewModel.addTodo()
        }

    }
}