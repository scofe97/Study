package com.example.ch3_4.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ch3_4.model.Review

@Dao
interface ReviewDao {

    @Query("SELECT * FROM review WHERE uid = :uid")
    fun getOneReview(uid: Int) : Review

    // 기존에 있는값이면 업데이트 하는형식으로 사용할 수 있음
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveReview(review: Review)
}