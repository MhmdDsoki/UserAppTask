package com.example.userapptask.data.albumsData

import androidx.room.withTransaction
import com.example.userapptask.api.ApiUser
import com.example.userapptask.data.userData.UserDatabase
import com.example.userapptask.utils.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random

class AlbumRepository @Inject constructor(
    private val api: ApiUser,
    private val db: AlbumDatabase,
    private val db1: UserDatabase

) {
    private var iid:Int= Random.nextInt(10)

    private val albumsDao = db.albumDao()
    private val userDao = db1.userDao()

    fun getUsers() = networkBoundResource(
        query = {
            userDao.getTheUser(iid)
        },
        fetch = {
            delay(2000)
            api.getUser(Id=iid)
        },
        saveFetchResult = { user ->
            db.withTransaction {
                userDao.deleteAllUsers()
                userDao.insertUsers(user)
            }
        }
    )

    fun getAlbums() = networkBoundResource(
        query = {
            albumsDao.getAllAlbums(userid = 6)
        },
        fetch = {
            delay(2000)
            api.getAlbumsByUserId(6)
        },
        saveFetchResult = { albumsList ->
            db.withTransaction {
                albumsDao.deleteAllAlbums()
                albumsDao.insertAlbums(albumsList)
            }
        }
    )
}

