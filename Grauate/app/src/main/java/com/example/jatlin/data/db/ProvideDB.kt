package com.example.jatlin.data.db

import android.content.Context
import android.service.autofill.UserData
import androidx.room.Room

internal fun provideDB(context: Context): UserDatabase =
    Room.databaseBuilder(context, UserDatabase::class.java, UserDatabase.DB_NAME).build()

internal fun provideToDoDao(database: UserDatabase) = database.productDao()