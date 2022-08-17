package com.example.userapptask.data.photoData

import androidx.room.withTransaction
import com.example.userapptask.api.ApiUser
import com.example.userapptask.data.albumsData.AlbumDatabase
import com.example.userapptask.data.albumsData.Albums
import com.example.userapptask.utils.networkBoundResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.random.Random

class PhotoRepository @Inject constructor(
    private val api: ApiUser,//web services
    private val db: PhotoDataBase,//roomdb,
    db1:AlbumDatabase
){
    private var iid:Int= Random.nextInt(100)
    private val photosDao = db.photoDao()
    private val albumDao = db1.albumDao()


    fun searchPhotos(searchQuery: String): Flow<List<Photos>> {
        return photosDao.searchPhotos(searchQuery)
    }

    fun getId():Albums{
        return albumDao.getAlbumId()
    }

    fun getPhotos() = networkBoundResource(
        query = {
           photosDao.getAllPhotos(id = getId().id)
        },
        fetch = {
            delay(2000)
             api.getPhotoAlbums(id = getId().id)
        },
        saveFetchResult = {photosList ->
            db.withTransaction {
                photosDao.deleteAllPhotos()
                photosDao.insertPhotos(photosList)
            }
        }
    )
}