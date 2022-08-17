package com.example.userapptask.di

import android.app.Application
import androidx.room.Room
import com.example.userapptask.api.ApiUser
import com.example.userapptask.data.albumsData.AlbumDatabase
import com.example.userapptask.data.photoData.PhotoDataBase
import com.example.userapptask.data.userData.UserDatabase
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserAppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(ApiUser.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addCallAdapterFactory(RxJavaCallAdapterFactory
                .create())
            .build()

    val okHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .callTimeout(5, TimeUnit.SECONDS).build()
    @Provides
    @Singleton
    fun provideApplicationApi(retrofit: Retrofit): ApiUser =
        retrofit.create(ApiUser::class.java)

    @Provides
    @Singleton
    fun provideDatabaseUser(app: Application) : UserDatabase =
        Room.databaseBuilder(app, UserDatabase::class.java,"users_database" )
            .fallbackToDestructiveMigration()
            .build()

//    @Provides
////    @Singleton
////    fun provideAdapter(app: Application): PhotosAdapter = PhotosAdapter()

    @Provides
    @Singleton
    fun provideDatabasePhoto(app: Application) : PhotoDataBase =
        Room.databaseBuilder(app, PhotoDataBase::class.java, "photos_database")//photo_database
            .fallbackToDestructiveMigration()
            .addMigrations()
            //.fallbackToDestructiveMigrationOnDowngrade()//new line
            .build()

    @Provides
    @Singleton
    fun provideDatabaseAlbum(app: Application) : AlbumDatabase =
        Room.databaseBuilder(app, AlbumDatabase::class.java, "album_database")//album_database
            .fallbackToDestructiveMigration()
            .build()

//@Provides
//@Singleton
//fun provideRanNum(RandomNum:RandomNum): RandomNum = RandomNum()


}
