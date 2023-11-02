package ghar.learn.cognizant.apptddapproach.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class ArtFragmentFactory @Inject constructor(
    private val artRecyclerAdapter: ArtRecyclerAdapter,
    private val imageRecyclerAdapter: ImageRecyclerAdapter,
    private val glide : RequestManager
) : FragmentFactory() {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            ArtsFragment::class.java.name -> ArtsFragment(artRecyclerAdapter)
            ArtsDetailFragment::class.java.name -> ArtsDetailFragment(glide)
            ImageApiFragment::class.java.name -> ImageApiFragment(imageRecyclerAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}