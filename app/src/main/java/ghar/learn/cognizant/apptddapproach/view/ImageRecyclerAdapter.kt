package ghar.learn.cognizant.apptddapproach.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import javax.inject.Inject
import ghar.learn.cognizant.apptddapproach.databinding.ImageSearchResultRowBinding as ImageSearchResultRowBinding

class ImageRecyclerAdapter @Inject constructor(
    private val glide: RequestManager,
) : RecyclerView.Adapter<ImageViewHolder>() {

    /** ItemClick handling */
    private var itemClickListener: ((String) -> Unit)? = null

    /** DiffUtil for ArtAdapter list-item diffs */
    private val diffUtil = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
    private val imagesListDiffer: AsyncListDiffer<String> = AsyncListDiffer(this, diffUtil)
//    var images: List<String>
//        get() = imageDiffUtil.currentList
//        set(value) = imageDiffUtil.submitList(value)

    var images : List<String>
        get() = imagesListDiffer.currentList
        set(value) = imagesListDiffer.submitList(value)

    /** DiffUtil for ArtAdapter list-item diffs ENDS HERE */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val imageSearchResultRowBinding = ImageSearchResultRowBinding.inflate(layoutInflater)
        return ImageViewHolder(imageSearchResultRowBinding)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun setOnItemClickListener(listener : (String) -> Unit) {
        itemClickListener = listener
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUrl: String = images[position]
        holder.imageResultItem.apply {
            glide.load(imageUrl).into(searchedImageView)
            searchedImageView.setOnClickListener {
                itemClickListener?.let {
                    it(imageUrl)
                }
            }

        }


    }

}

class ImageViewHolder(internal val imageResultItem : ImageSearchResultRowBinding) : RecyclerView.ViewHolder(imageResultItem.root)
