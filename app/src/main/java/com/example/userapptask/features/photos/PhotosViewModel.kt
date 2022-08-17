package com.example.userapptask.features.photos

import com.example.userapptask.data.photoData.PhotoRepository
import com.example.userapptask.data.photoData.Photos
import androidx.hilt.Assisted
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val repository: PhotoRepository,
    @Assisted state: SavedStateHandle
) : ViewModel() {

    val photos = repository.getPhotos().asLiveData()

    fun searchDatabase(searchQuery: String): LiveData<List<Photos>> {
        return repository.searchPhotos(searchQuery).asLiveData()
    }

}
//fun searchPhotos(query: String) {
//        photos.value?: query
//      }