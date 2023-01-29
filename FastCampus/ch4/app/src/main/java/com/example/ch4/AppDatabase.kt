package com.example.ch4

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ch4.dao.HistoryDao
import com.example.ch4.model.History

@Database(entities = [History::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract  fun historyDao() : HistoryDao
}