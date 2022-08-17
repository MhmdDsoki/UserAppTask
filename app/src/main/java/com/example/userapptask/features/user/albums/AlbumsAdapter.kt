package com.example.userapptask.features.user.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.userapptask.data.albumsData.Albums
import com.example.userapptask.databinding.AlbumsItemBinding

class AlbumsAdapter(private val listener: OnItemClickListener):
    ListAdapter<Albums, AlbumsAdapter.AlbumsViewHolder>(AlbumsComparator()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val binding = AlbumsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumsViewHolder(binding)
    }
    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int){
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class AlbumsViewHolder(private val binding: AlbumsItemBinding) :
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
        fun bind(albums: Albums) {
            binding.apply {
                tvAlbumsUserId.text = albums.userId.toString()
                tvAlbumsId.text = albums.id.toString()
                tvAlbumsTitle.text = albums.title
            }
        }
    }
    interface OnItemClickListener {
        fun onItemClick(albums: Albums)
    }
    class AlbumsComparator : DiffUtil.ItemCallback<Albums>() {
        override fun areItemsTheSame(oldItem: Albums, newItem: Albums) =
            oldItem.userId == newItem.userId

        override fun areContentsTheSame(oldItem: Albums, newItem: Albums) =
            oldItem == newItem
    }



}