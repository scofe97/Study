package com.example.jatlin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jatlin.data.entity.ProductEntity
import com.example.jatlin.data.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)

abstract class UserDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "UserDatabase.db"
    }

    abstract fun productDao(): UserDao
}