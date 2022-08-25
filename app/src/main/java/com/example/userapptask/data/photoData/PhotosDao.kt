package com.example.userapptask.data.photoData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.userapptask.data.albumsData.Albums
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotosDao {

    @Query("SELECT * FROM photos WHERE title LIKE (:searchQuery)")
    fun searchPhotos(searchQuery: String):(Flow<List<Photos>>)

    @Query("SELECT * FROM photos WHERE albumId IN (:id)")
     fun getAllPhotos(id:Int):(Flow<List<Photos?>?>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(photosList: List<Photos?>?)

    @Query("DELETE FROM photos")
    suspend fun deleteAllPhotos()
}
