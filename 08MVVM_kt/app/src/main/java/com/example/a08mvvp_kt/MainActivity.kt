package com.example.a08mvvp_kt

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a08mvvp_kt.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var vb: ActivityMainBinding
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
            viewModel.saveOrUpdate(currentName, currentEmail)

        }
        vb.btnClearAllDelete.setOnClickListener {
            viewModel.deleteOrClearAll()
        }

        viewModel.getSavedSubscribers().observe(this) {
            Log.d("MVVM", it.toString())
            vb.rvContainer.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = SubscriberAdapter(it) { subscriber ->
                    viewModel.initUpdateAndDeleteButton(subscriber)

//                    Toast.makeText(applicationContext, "${subscriber.name}", Toast.LENGTH_SHORT)
//                        .show()
                }
            }
        }

        viewModel.message.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
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