package ghar.learn.cognizant.apptddapproach.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ghar.learn.cognizant.apptddapproach.R
import ghar.learn.cognizant.apptddapproach.databinding.FragmentImageApiBinding
import ghar.learn.cognizant.apptddapproach.util.Status
import ghar.learn.cognizant.apptddapproach.viewmodel.ArtViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class ImageApiFragment @Inject constructor(
    private val imageRecyclerAdapter: ImageRecyclerAdapter
): Fragment(R.layout.fragment_image_api) {

    lateinit var viewModel : ArtViewModel
    private var imageApiBinding: FragmentImageApiBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[ArtViewModel::class.java]
        val binding = FragmentImageApiBinding.bind(view)
        imageApiBinding = binding

        // Search image using edit-able field and navigate back to DetailsScreen (@ArtDetailFragment)
        var job : Job? = null
        binding.etSearchText.addTextChangedListener {et ->
            job?.cancel()
            job = lifecycleScope.launch {
                delay(1000)
                et?.let {
                    if(it.toString().isNotEmpty()) {
                        viewModel.searchImage(it.toString())
                    }
                }
            }
        }

        setUpObservers()
        binding.recyclerViewImageSearch.apply {
            adapter = imageRecyclerAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
        imageRecyclerAdapter.setOnItemClickListener {
            viewModel.setSelectedImage(it)
            findNavController().popBackStack()
        }
    }

    private fun setUpObservers(){
        viewModel.images.observe(viewLifecycleOwner, Observer {apiResource ->
            when(apiResource.status) {
                Status.SUCCESS -> {
                    val urls = apiResource.data?.hits?.map {imageResult ->
                        imageResult.previewURL
                    }
                    imageRecyclerAdapter.images = ((urls ?: listOf()) as List<String>)
                    imageApiBinding?.pBarSearchImage?.visibility = View.GONE
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), apiResource.message ?: "Error", Toast.LENGTH_LONG).show()
                    imageApiBinding?.pBarSearchImage?.visibility = View.GONE
                }
                Status.LOADING -> {
                    imageApiBinding?.pBarSearchImage?.visibility = View.VISIBLE
                }
            }

        })
    }

    override fun onDestroyView() {
        imageApiBinding = null
        super.onDestroyView()
    }

}