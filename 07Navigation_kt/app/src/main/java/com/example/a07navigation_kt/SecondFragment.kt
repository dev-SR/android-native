package com.example.a07navigation_kt

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.a07navigation_kt.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {
    private lateinit var fvb: FragmentSecondBinding
//    private args:/**/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fvb = FragmentSecondBinding.inflate(inflater, container, false)
        val view = fvb.root

        arguments?.let {
            val args = SecondFragmentArgs.fromBundle(it)
            val number = args.number
            Log.d("Fragments", number.toString())
            fvb.tvFrag2.text = "Sent From Frag1 : ${number.toString()}"
        }

        fvb.tvFrag2.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        }
        return view
    }
}