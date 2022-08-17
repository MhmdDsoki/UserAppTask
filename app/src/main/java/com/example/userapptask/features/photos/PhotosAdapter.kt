package com.example.userapptask.features.photos

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.userapptask.R
import com.example.userapptask.data.photoData.Photos
import com.example.userapptask.databinding.PhotoItemBinding
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class PhotosAdapter (private val listener: OnItemClickListener,var photosList : ArrayList<Photos>):
    ListAdapter<Photos, PhotosAdapter.PhotosViewHolder>(PhotoComparator()), Filterable {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val binding = PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotosViewHolder(binding)
    }
    private var oldData = emptyList<Photos>()
    var photosFilterList = ArrayList<Photos>()
    //var photosList = ArrayList<Photos>()
    init {
        photosFilterList = photosList
    }
    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int){
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class PhotosViewHolder(private val binding: PhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(photos: Photos) {
            binding.apply {
//                Glide.with(itemView).load(photos.thumbnailUrl).placeholder(R.drawable.img_placeholder)
//                .error(R.drawable.img_placeholder).override(SIZE_ORIGINAL).into(imgPhotosThumbnailUrl)
//                 Glide.with(itemView).load(photos.url).into(imgPhotosUrl)
                Picasso.get().load(photos.thumbnailUrl)
                    .placeholder(R.drawable.img_placeholder)
                    .error(R.drawable.img_placeholder)
                    .into(imgPhotosThumbnailUrl)
//                Picasso.get().load(photos.url)
//                    .placeholder(R.drawable.img_placeholder)
//                    .error(R.drawable.img_placeholder)
//                    .into(imgPhotosUrl)
                tvPhotosAlbumid.text = photos.albumId.toString()
                tvPhotosTitle.text = photos.title
                tvPhotosId.text = photos.id.toString()
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(photo: Photos)
    }
    class PhotoComparator : DiffUtil.ItemCallback<Photos>() {
        override fun areItemsTheSame(oldItem: Photos, newItem: Photos) =
            oldItem.albumId == newItem.albumId

        override fun areContentsTheSame(oldItem: Photos, newItem: Photos) =
            oldItem == newItem
    }


    override fun getFilter(): Filter {
        return customFilter
    }
    fun setData(newData: List<Photos>){
        oldData = newData
        notifyDataSetChanged()
    }
    private val customFilter = object : Filter() {
        override fun performFiltering(charSequence: CharSequence?): FilterResults {
            val searchString = charSequence.toString()
            if (searchString.isEmpty()) {
                photosFilterList = photosList
            } else {
                val resultFilteredList = ArrayList<Photos>()
                for (row in photosList) {
                    // search for user title
                    if (row.title.lowercase(Locale.ROOT)
                            .contains(searchString.lowercase(Locale.ROOT))
                    ) {
                        resultFilteredList.add(row)
                    }
                }
                photosFilterList = resultFilteredList
            }

            val filterResults= FilterResults() //android.widget.Fi
            filterResults.values = photosFilterList
            return filterResults
        }
        @SuppressLint("NotifyDataSetChanged")
        @SuppressWarnings("unchecked")
        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            photosFilterList = p1?.values as ArrayList<Photos>
            notifyDataSetChanged()
        }
    }
}





