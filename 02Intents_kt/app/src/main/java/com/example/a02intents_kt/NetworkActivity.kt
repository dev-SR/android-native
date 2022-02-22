package com.example.a02intents_kt

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a02intents_kt.databinding.ActivityNetworkBinding

class NetworkActivity : AppCompatActivity() {
    private lateinit var vb: ActivityNetworkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityNetworkBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)

        vb.btnStatusBar.setOnClickListener {
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = cm.activeNetworkInfo
            val isConnected: Boolean = networkInfo != null && networkInfo.isConnected
            vb.tvStatus.text = if (isConnected) "Connected" else "Disconnected"
        }
    }
}