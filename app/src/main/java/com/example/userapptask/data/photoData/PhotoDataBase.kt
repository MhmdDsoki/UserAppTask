package com.example.userapptask.data.photoData
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Photos::class], version =3, exportSchema = false)
abstract class PhotoDataBase : RoomDatabase() {
    abstract fun photoDao(): PhotosDao
}