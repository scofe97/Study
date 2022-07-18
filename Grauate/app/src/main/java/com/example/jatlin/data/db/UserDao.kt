package com.example.jatlin.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jatlin.data.entity.UserEntity


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentUser(UserEntity : UserEntity)

    @Query("SELECT * FROM UserEntity WHERE userUid =:uid")
    suspend fun getCurrentUser(uid: String): UserEntity?
}