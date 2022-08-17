package com.example.userapptask.data.photoData

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "photos")
data class Photos (
    @SerializedName("albumId")
    @Expose
    val albumId: Int,
    @SerializedName("thumbnailUrl")
    @Expose
    val thumbnailUrl: String,
    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("url")
    @Expose
    val url: String):Serializable

