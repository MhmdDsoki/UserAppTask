package com.example.userapptask.features.photos

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userapptask.R
import com.example.userapptask.data.photoData.Photos
import com.example.userapptask.databinding.PhotoFragmentBinding
import com.example.userapptask.utils.Resource

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoFragment():Fragment(R.layout.photo_fragment),
    PhotosAdapter.OnItemClickListener, SearchView.OnQueryTextListener {
    private val viewModel by viewModels<PhotosViewModel>()

    private var _binding: PhotoFragmentBinding?=null
    private val binding get() = _binding!!

    private val args by navArgs<PhotoFragmentArgs>()
    var photosList = ArrayList<Photos>()
    //private var photosAdapter: PhotosAdapter = TODO()
    val photosAdapter = PhotosAdapter(this, photosList)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = PhotoFragmentBinding.bind(view)

        //val photosAdapter = PhotosAdapter(this)
        binding.apply {
            recyclerView.apply {
                adapter=photosAdapter
                layoutManager = LinearLayoutManager(context)
            }
            val albums = args.albums
            tvAlbumsTitle.text = albums.title
            tvAlbumsId.text=albums.userId.toString()
            tvAlbumsUserId.text=albums.id.toString()

            //i want to use the album id as a parameter for albums id at photos
            lifecycleScope.launchWhenCreated { viewModel.photos.observe(viewLifecycleOwner) { result ->
                photosAdapter.submitList(result.data)
                //args.albums.id
                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                textViewError.isVisible = result is Resource.Error<*> && result.data.isNullOrEmpty()
                textViewError.text = result.error?.localizedMessage
            }
            }

        }
        setHasOptionsMenu(true)
    }

    override fun onItemClick(photos: Photos) {
        val action =PhotoFragmentDirections.actionPhotoFragmentToFragmentDetails(photos)
        findNavController().navigate(action)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_photo_search, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
        searchView.clearFocus()
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"
        viewModel.searchDatabase(searchQuery).observe(this) { result ->
            result.let {
                photosAdapter.submitList(result)
            }
        }
    }

    override fun onQueryTextSubmit(searchQuery: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(searchQuery: String?): Boolean {
        if(searchQuery != null){
            //binding.recyclerView.scrollToPosition(0)
            searchDatabase(searchQuery)
        }
        return true
    }


}


