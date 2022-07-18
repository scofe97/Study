package com.example.jatlin.data.repository

import android.util.Log
import com.example.jatlin.DBKey
import com.example.jatlin.data.db.UserDao
import com.example.jatlin.data.entity.ProductEntity
import com.example.jatlin.data.entity.UserEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultFirebaseRepository(
    private val ioDispatcher : CoroutineDispatcher,
    private val userDao : UserDao
) : FirebaseRepository{

    private val firebaseDB: DatabaseReference by lazy { Firebase.database.reference }

    override suspend fun getCurrentUser(uid : String): UserEntity? = withContext(ioDispatcher) {
        return@withContext userDao.getCurrentUser(uid)
    }

    override suspend fun insertUser(user: UserEntity) = withContext(ioDispatcher) {

        val userDB = firebaseDB.child(DBKey.DB_USERS)
        userDB.child(user.userUid).child("userUid").setValue(user.userUid)
        userDB.child(user.userUid).child("userEmail").setValue(user.userEmail)
        userDB.child(user.userUid).child("userName").setValue(user.userName?: "익명")
        userDB.child(user.userUid).child("userPhoneNumber").setValue(user.userPhoneNumber)
        userDB.child(user.userUid).child("userImage").setValue(user.userImage)
        userDao.insertCurrentUser(user)
    }
}