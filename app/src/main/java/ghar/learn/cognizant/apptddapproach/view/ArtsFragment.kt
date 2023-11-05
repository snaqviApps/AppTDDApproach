package ghar.learn.cognizant.apptddapproach.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ghar.learn.cognizant.apptddapproach.R
import ghar.learn.cognizant.apptddapproach.databinding.FragmentArtsBinding
import ghar.learn.cognizant.apptddapproach.viewmodel.ArtViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/**
 * a feature whereby layout can be added to super constructor, skipping the OnCreateView()
 * here we can use ViewBinding
 * */
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ArtsFragment @Inject constructor(
    private val artRecyclerAdapter: ArtRecyclerAdapter
) : Fragment(R.layout.fragment_arts) {

//    lateinit var binding : FragmentArtsBinding            when we do not need to use in onDestroy()
    private var fragmentArtsBinding : FragmentArtsBinding? = null
    lateinit var artsViewModel: ArtViewModel

    /** Swipe-to-delete viewItem:  RIGHT or LEFT */
    private val swipeCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition = viewHolder.layoutPosition
            val selectedArt = artRecyclerAdapter.arts[layoutPosition]
            artsViewModel.deleteArt(selectedArt)
        }
    }
    // swipe-controller ---RIGHT or LEFT--- for deletion ENDS

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        artsViewModel = ViewModelProvider(requireActivity())[ArtViewModel::class.java]
        val binding = FragmentArtsBinding.bind(view)
        fragmentArtsBinding = binding
        setObservers()
        binding.recyclerViewArt.adapter = artRecyclerAdapter
        binding.recyclerViewArt.layoutManager = LinearLayoutManager(requireContext())
        ItemTouchHelper(swipeCallback).attachToRecyclerView(binding.recyclerViewArt)

        binding.fab.setOnClickListener {
            findNavController().navigate(
                ArtsFragmentDirections.actionArtsFragmentToArtsDetailFragment()
            )
        }
    }

    private fun setObservers(){
        artsViewModel.arts.observe(viewLifecycleOwner, Observer {
            artRecyclerAdapter.arts = it
        })
    }

    override fun onDestroyView() {
        fragmentArtsBinding = null
        super.onDestroyView()
    }
}