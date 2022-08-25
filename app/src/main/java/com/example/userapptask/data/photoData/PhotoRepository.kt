package com.example.userapptask.data.photoData

import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.navDeepLink
import androidx.room.withTransaction
import com.example.userapptask.api.ApiUser
import com.example.userapptask.data.albumsData.AlbumDatabase
import com.example.userapptask.data.albumsData.Albums
import com.example.userapptask.features.user.albums.AlbumsViewModel
import com.example.userapptask.utils.networkBoundResource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.random.Random

class PhotoRepository @Inject constructor(
    private val api: ApiUser,//web services
    private val db: PhotoDataBase,//roomdb,
    private val db1:AlbumDatabase
){
    private var iid:Int= Random.nextInt(100)
    private val photosDao = db.photoDao()
    private val albumDao = db1.albumDao()


    fun searchPhotos(searchQuery: String): Flow<List<Photos>> {
        return photosDao.searchPhotos(searchQuery)
    }

    suspend fun getId():Albums{
//        CoroutineScope(Dispatchers.IO).launch {
//            albumDao.getAlbumId()
//        }

        return albumDao.getAlbumId()
    }
//suspend fun getId(): List<Albums> = withContext(Dispatchers.IO) {
//    albumDao.getAlbumId()
//}

     fun getPhotos() = networkBoundResource(
        query = {
         runBlocking { photosDao.getAllPhotos(id =getId().id ) }
        },
        fetch = {
            runBlocking {
                delay(2000)
                api.getPhotoAlbums(id = getId().id)
            }
        },
        saveFetchResult = {photosList ->
            db.withTransaction {
                photosDao.deleteAllPhotos()
                photosDao.insertPhotos(photosList)
            }
        }
    )
}