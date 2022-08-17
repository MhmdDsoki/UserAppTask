package com.example.userapptask.data.userData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlin.random.Random

@Dao
interface UserDao{
    //@Query("SELECT id FROM users ORDER BY random() LIMIT 1")
    //"SELECT * FROM users WHERE id IN (:Id)"
    @Query("SELECT * FROM users WHERE id IN (:Id)")
    fun getTheUser(Id:Int):Flow<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(user: User)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
}