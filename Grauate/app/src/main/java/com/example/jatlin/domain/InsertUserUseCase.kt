package com.example.jatlin.domain

import com.example.jatlin.data.entity.UserEntity
import com.example.jatlin.data.repository.FirebaseRepository

class InsertUserUseCase(
    private val firebaseRepository: FirebaseRepository
) : UseCase {

    suspend operator fun invoke(userEntity : UserEntity){
        return firebaseRepository.insertUser(userEntity)
    }
}