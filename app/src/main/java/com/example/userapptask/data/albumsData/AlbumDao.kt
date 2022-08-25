package com.example.userapptask.data.albumsData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface AlbumDao {

    @Query("SELECT * FROM albums WHERE userId IN (:userid)")
    fun getAllAlbums(userid:Int):(Flow<List<Albums?>?>)

    @Query("SELECT * FROM Albums")
    suspend fun getAlbumId():Albums

    @Query("SELECT * FROM albums WHERE id IN (:Id)")
    fun getAllAlbumsId(Id:Int):(Albums)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbums(albumsList: List<Albums?>?)

    @Query("DELETE FROM albums")
    suspend fun deleteAllAlbums()

}