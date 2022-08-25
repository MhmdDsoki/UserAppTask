package com.example.userapptask.data.albumsData

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "albums")
data class Albums(
    @SerializedName("userId")
    @Expose
    val userId: Int,
    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("title")
    @Expose
    val title: String
): Serializable