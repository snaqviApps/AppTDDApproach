package ghar.learn.cognizant.apptddapproach.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import ghar.learn.cognizant.apptddapproach.databinding.FragmentArtDetailsBinding
import ghar.learn.cognizant.apptddapproach.R

class ArtsDetailFragment : Fragment(R.layout.fragment_art_details) {

    private var fragmentBinding : FragmentArtDetailsBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentArtDetailsBinding.bind(view)
        fragmentBinding = binding
        binding.artImageView.setOnClickListener {
            findNavController().navigate(ArtsDetailFragmentDirections.actionArtsDetailFragmentToImageApiFragment())
        }

        // handle back-button interaction
        backButtonPress(requireActivity())


    }

    private fun backButtonPress(requireActivity: FragmentActivity) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity.onBackPressedDispatcher.addCallback(callback)
    }

    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }
}