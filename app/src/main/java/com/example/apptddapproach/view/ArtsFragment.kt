package com.example.apptddapproach.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.apptddapproach.R
import com.example.apptddapproach.databinding.FragmentArtsBinding

/**
 * a feature whereby layout can be added to super constructor, skipping the OnCreateView()
 * here we can use ViewBinding
 * */
class ArtsFragment : Fragment(R.layout.fragment_arts) {

//    lateinit var binding : FragmentArtsBinding
    var fragmentArtsBinding : FragmentArtsBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentArtsBinding.bind(view)
        fragmentArtsBinding = binding
        binding.fab.setOnClickListener {
            findNavController().navigate(ArtsFragmentDirections.actionArtsFragmentToArtsDetailFragment())
        }
    }

    override fun onDestroy() {
        fragmentArtsBinding = null
        super.onDestroy()
    }
}