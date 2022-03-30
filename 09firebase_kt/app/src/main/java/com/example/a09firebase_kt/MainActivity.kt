package com.example.a09firebase_kt

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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


        // ViewModel Initialization:
        val factory = FireStoreViewModel.Factory(FirebaseRepository())
        viewModel = ViewModelProvider(this, factory)[FireStoreViewModel::class.java]

        //Observe LiveData
        viewModel.getTodosAsLiveData().observe(this) {
            when (it) {
                is UiState.Loading -> {
                    Log.d("FireStore", "LOADING")

                    showProgress()
                }
                is UiState.Success -> {
                    hideProgress()
                    Log.d("FireStore", "${it.data}")
                    var value = "";
                    it.data.forEach { todo: Todo -> value += "\n${todo.title}" }
                    vb.textView.text = value
                }
                is UiState.Failed -> {
                    Log.d("FireStore", "${it.message}")
                }
            }

        }
        vb.button.setOnClickListener {
            viewModel.addTodo()
        }

        viewModel.addMessage.observe(this) {
            when (it) {
                is UiState.Loading -> {
                    showProgress()

                }
                is UiState.Success -> {
                    Toast.makeText(applicationContext, "${it.data.title} Added", Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun showProgress() {
        vb.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        vb.progressBar.visibility = View.GONE
    }
}