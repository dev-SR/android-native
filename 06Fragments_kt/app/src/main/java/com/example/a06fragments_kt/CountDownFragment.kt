package com.example.a06fragments_kt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a06fragments_kt.databinding.FragmentCountDownBinding


class CountDownFragment : Fragment() {
    private lateinit var vb: FragmentCountDownBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vb = FragmentCountDownBinding.inflate(inflater, container, false)
        var count = 0

        vb.btnMin.setOnClickListener {
            vb.tvMin.text = (count--).toString()
        }
        return vb.root
    }


}
