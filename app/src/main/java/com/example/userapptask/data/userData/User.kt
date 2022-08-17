package com.example.userapptask.data.userData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: Int,
    val username: String,
    val address:Address
)
{}
//    {
//        data class Geo (
//            val lat: String? ,
//            val lng: String?
//        )
//    }