package ghar.learn.cognizant.apptddapproach.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import ghar.learn.cognizant.apptddapproach.databinding.FragmentArtDetailsBinding
import ghar.learn.cognizant.apptddapproach.R
import ghar.learn.cognizant.apptddapproach.util.Utils
import ghar.learn.cognizant.apptddapproach.viewmodel.ArtViewModel
import javax.inject.Inject

@AndroidEntryPoint
class ArtsDetailFragment @Inject constructor(
    val glide: RequestManager
) : Fragment(R.layout.fragment_art_details) {

    lateinit var viewModel: ArtViewModel

    private var fragmentBinding: FragmentArtDetailsBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ArtViewModel::class.java]

        val binding = FragmentArtDetailsBinding.bind(view)
        fragmentBinding = binding

        setUpObservers()

        binding.artImageView.setOnClickListener {
            findNavController().navigate(ArtsDetailFragmentDirections.actionArtsDetailFragmentToImageApiFragment())
        }

//        onBackPressed()
        val callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.setSelectedImage("")
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callBack)

        /**  check if inputs for crafting 'Art' are with valid-format, if it is a Pass, it is added to DB */
        binding.apply {
            bSave.setOnClickListener {
                viewModel.makeArt(
                    etArtName.text.toString(),
                    etArtistName.text.toString(),
                    etArtYear.text.toString())
            }
        }
    }

    private fun setUpObservers() {
//        viewModel.apply {

            // Info overall about the App Status
            viewModel.imageSelected.observe(viewLifecycleOwner, Observer { url ->
                println("image-selected-url: $url")
                fragmentBinding?.apply {
                        glide.load(url).into(artImageView)
                }
            })

            viewModel.insertArtMsg.observe(viewLifecycleOwner, Observer { resource ->
                when(resource.status){
                    Utils.Status.SUCCESS -> {
                        Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
                        findNavController().navigateUp()
                        viewModel.resetInsertArtMsg()
                    }
                    Utils.Status.ERROR -> {
                        Toast.makeText(requireContext(), resource.message ?: "Error", Toast.LENGTH_LONG).show()
                    }
                    Utils.Status.LOADING -> {  }

                }
            })
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBinding = null
    }
}