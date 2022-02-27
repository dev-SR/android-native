package com.example.a06fragments_kt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class CountUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_count_up, container, false)
        val btnAdd = fragmentView.findViewById<Button>(R.id.bntAdd)
        val tvAdd = fragmentView.findViewById<TextView>(R.id.tvAdd)
        var count = 0
        btnAdd.setOnClickListener {
            tvAdd.text = (count++).toString()
        }
        return fragmentView
    }


}