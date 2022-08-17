package com.example.userapptask.features.user.albums
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.userapptask.data.albumsData.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
//possimus expedita ut
@HiltViewModel
class AlbumsViewModel @Inject constructor(
    repository: AlbumRepository
) : ViewModel() {
    val albums = repository.getAlbums().asLiveData()
    val user =repository.getUsers().asLiveData()
}