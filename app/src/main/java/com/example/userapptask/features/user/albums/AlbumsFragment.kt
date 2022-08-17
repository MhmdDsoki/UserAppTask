package com.example.userapptask.features.user.albums

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userapptask.R
import com.example.userapptask.data.albumsData.Albums
import com.example.userapptask.databinding.AlbumsFragmentBinding
import com.example.userapptask.utils.Resource

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumsFragment : Fragment(R.layout.albums_fragment),AlbumsAdapter.OnItemClickListener {
    private val viewModelAlbums by viewModels<AlbumsViewModel>()
    //private val viewModelUser by viewModels<UserViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = AlbumsFragmentBinding.bind(view)
        val albumsAdapter = AlbumsAdapter(this)
        binding.apply {
            recyclerViewAlbums.apply {
                adapter = albumsAdapter
                layoutManager = LinearLayoutManager(context)
            }

            viewModelAlbums.user.observe(viewLifecycleOwner)
            {
                userName.text = it.data?.username
                streettv.text=it.data?.address?.street
                citytv.text=it.data?.address?.city
            }

            viewModelAlbums.albums.observe(viewLifecycleOwner) { result ->
                albumsAdapter.submitList(result.data)
//                 recyclerViewAlbums.setOnClickListener(View.OnClickListener {
//                     val context: Context = it.getContext()
//                     val intent = Intent(context, PhotoFragment::class.java)
//                     context.startActivity(intent)
//}
//                 )
                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                textViewError.isVisible = result is Resource.Error<*> && result.data.isNullOrEmpty()
                textViewError.text = result.error?.localizedMessage
            }
        }
    }

    override fun onItemClick(albums: Albums) {
        val action = AlbumsFragmentDirections.actionAlbumsFragmentToPhotoFragment(albums)
        findNavController().navigate(action)
    }
}

/*LiveData is an observable data holder class that is lifecycle-aware.

Some characteristics of LiveData:

LiveData holds data; LiveData is a wrapper that can be used with any type of data.
LiveData is observable, which means
that an observer is notified when the data held by the LiveData object changes.
LiveData is lifecycle-aware. When you attach an observer to the LiveData,
the observer is associated with a LifecycleOwner (usually an activity or fragment).
 The LiveData only updates observers that are in an active lifecycle state
 such as STARTED or RESUMED.
 You can read more about LiveData and observation*/
