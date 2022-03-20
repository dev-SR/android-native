package com.example.a07navigation_kt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.a07navigation_kt.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {
    private lateinit var fvb: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fvb = FragmentFirstBinding.inflate(inflater, container, false)
        return fvb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fvb.tvFrag1.setOnClickListener {
//            Navigation.findNavController(view)
//                .navigate(R.id.action_firstFragment_to_secondFragment)
//            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(100)
            findNavController().navigate(action)
        }
    }
}