package com.example.apptddapproach.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.apptddapproach.R
import com.example.apptddapproach.databinding.FragmentArtDetailsBinding

class ArtsDetailFragment : Fragment(R.layout.fragment_art_details) {

    private var fragmentBinding : FragmentArtDetailsBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentArtDetailsBinding.bind(view)
        fragmentBinding = binding

    }
}