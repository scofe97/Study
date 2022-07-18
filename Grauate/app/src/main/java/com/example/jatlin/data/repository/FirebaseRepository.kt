package com.example.jatlin.data.repository

import com.example.jatlin.data.entity.ProductEntity
import com.example.jatlin.data.entity.UserEntity

interface FirebaseRepository {

    suspend fun getCurrentUser(uid : String) : UserEntity?

    suspend fun insertUser(user : UserEntity)

}