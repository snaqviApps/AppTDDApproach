package ghar.learn.cognizant.apptddapproach.view

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewDebug.IntToString
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.RequestManager
import ghar.learn.cognizant.apptddapproach.R
import ghar.learn.cognizant.apptddapproach.databinding.ArtRowBinding
import ghar.learn.cognizant.apptddapproach.db.Art
import javax.inject.Inject

class ArtRecyclerAdapter @Inject constructor(
    private val glide : RequestManager
) : RecyclerView.Adapter<ArtRecyclerAdapter.ArtViewHolder>() {

    /** DiffUtil for ArtAdapter list-item diffs */
    private val diffUtil = object : DiffUtil.ItemCallback<Art>() {
        override fun areItemsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem == newItem
        }
    }
    private val artDiffUtil = AsyncListDiffer(this, diffUtil)
    var arts : List<Art>
        get() {
            return artDiffUtil.currentList
        }
        set(value) {
            return artDiffUtil.submitList(value)
        }
    /** DiffUtil for ArtAdapter list-item diffs ENDS HERE */


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)                     // approach with no view-binding
//        val view = layoutInflater.inflate(R.layout.art_row, parent, false)
//        return ArtViewHolder(view)                                                   // approach with no view-binding ENDS
        val layoutInflater = LayoutInflater.from(parent.context)
        val artRowBinding = ArtRowBinding.inflate(layoutInflater)
        return ArtViewHolder(artRowBinding)
    }

    override fun getItemCount(): Int {
        return arts.size
    }

    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
        val art = arts[position]
        holder.bind(art, glide)
    }

    class ArtViewHolder(private val rowItem: ArtRowBinding) : ViewHolder(rowItem.root) {
    fun bind(art: Art, glide: RequestManager) {
        rowItem.tvRowName.text = buildString { append("Art: ") }.plus(art.name)         // using buildString-approach to do the same as done #63, 64
        rowItem.tvRowArtistName.text = Resources.getSystem().getString(R.string.artist_name).plus(": ${art.artistName}")
        rowItem.tvRowYear.text = Resources.getSystem().getString(R.string.art_year).plus(art.year)
        glide.load(art.imageUrl).into(rowItem.imageEntry)
    }


}

}


