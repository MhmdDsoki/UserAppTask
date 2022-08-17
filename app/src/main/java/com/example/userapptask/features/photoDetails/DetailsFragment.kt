package com.example.userapptask.features.photoDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.userapptask.R
import com.example.userapptask.databinding.DetailsFragmentBinding
import com.squareup.picasso.Picasso


class DetailsFragment : Fragment(R.layout.details_fragment){

    private val args by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = DetailsFragmentBinding.bind(view)
        binding.apply {
            val photos = args.photos
            Picasso.get().load(photos.thumbnailUrl)
                .placeholder(R.drawable.img_placeholder)
                .error(R.drawable.img_placeholder)
                .into(thumbnailUrl)
        }
    }
}