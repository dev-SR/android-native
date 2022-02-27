package com.example.a06fragments_kt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.example.a06fragments_kt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var vb: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        val view = vb.root
        setContentView(view)

        vb.btnShow1.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.placeholder, CountUpFragment())
                .commit()
        }
        vb.btnShow2.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.placeholder, CountDownFragment())
                .commit()
        }


    }
}