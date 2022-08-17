package com.example.userapptask.api

import com.example.userapptask.data.albumsData.Albums
import com.example.userapptask.data.photoData.Photos
import com.example.userapptask.data.userData.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiUser {
    companion object {
        const val BASE_URL: String = "https://jsonplaceholder.typicode.com/"
    }
    /* Get user by id*/
    @GET("users/{id}")
    suspend fun getUser(@Path("id") Id:Int?): User

    /* Get albums by userId */
    @GET("albums")
    suspend fun getAlbumsByUserId(@Query("userId") userId: Int?): List<Albums>

    /* Get photos by albumsId */
    @GET("albums/{id}/photos")
    suspend fun getPhotoAlbums(@Path("id") id: Int?): List<Photos?>?


}