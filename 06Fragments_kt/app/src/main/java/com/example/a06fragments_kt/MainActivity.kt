package com.example.a06fragments_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.a06fragments_kt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var vb: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_container, OneFragment(), "OneFragmentTag")
            .commit()

        vb.btnShowFrag.setOnClickListener {
            val oneFragment =
                supportFragmentManager.findFragmentById(R.id.main_container) as OneFragment
            val data = "XXXXX"
            oneFragment.showFragmentMessage(data)
        }

    }

    fun showActivityMessage(data: String) {

        Toast.makeText(
            this, "This Message is from Activity\n" +
                    "Data receive from Fragment:$data", Toast.LENGTH_SHORT
        ).show()
    }
}