package com.example.a08mvvp_kt

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a08mvvp_kt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var vb: ActivityMainBinding

    //    private var count = 0
    private lateinit var viewModel: SubscriberViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)
        val repository = SubscriberRepository(applicationContext)
        val factory = SubscriberViewModel.Factory(repository)
        viewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)

        observeInputText()
        observeButtonText()
        vb.btnSaveOrUpdate.setOnClickListener {
            val currentName = vb.etName.text.toString()
            val currentEmail = vb.etEmail.text.toString()
            viewModel.save(currentName, currentEmail)
        }
        vb.btnClearAllDelete.setOnClickListener {
            viewModel.ClearAll()
        }
        viewModel.getSavedSubscribers().observe(this) {
            Log.d("MVVM", it.toString())
            vb.rvContainer.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = SubscriberAdapter(it)
            }
        }

    }

    private fun observeButtonText() {
        viewModel.saveOrUpdateButtonText.observe(this) {
            vb.btnSaveOrUpdate.text = it
        }
        viewModel.clearAllOrDeleteButtonText.observe(this) {
            vb.btnClearAllDelete.text = it
        }
    }

    private fun observeInputText() {
        viewModel.inputName.observe(this) {
            vb.etName.setText(it)
        }
        viewModel.inputEmail.observe(this) {
            vb.etEmail.setText(it)
        }
    }
}