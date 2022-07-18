package com.example.jatlin.domain

import com.example.jatlin.data.entity.UserEntity
import com.example.jatlin.data.repository.FirebaseRepository

class GetCurrentUserUseCase(
    private val firebaseRepository: FirebaseRepository
) : UseCase {

    suspend operator fun invoke(uid: String) : UserEntity? {
        return firebaseRepository.getCurrentUser(uid)
    }
}