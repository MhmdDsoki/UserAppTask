package com.example.userapptask.data.albumsData

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [Albums::class], version =2,exportSchema = false)
abstract class AlbumDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao

}