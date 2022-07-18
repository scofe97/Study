package com.example.ch3_4

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.ch3_4.dao.HistoryDao
import com.example.ch3_4.dao.ReviewDao
import com.example.ch3_4.model.History
import com.example.ch3_4.model.Review

// 버전 1에서 historyDao 하나로 만듬
// 그러므로 원래 DB를 지우거나, 변경하는 방법을 사용해야함
// 삭제 -> 가상장치에서 앱 우클릭 -> 삭제하고 실행하면됨
@Database(entities = [History::class, Review::class ], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
    abstract fun reviewDao(): ReviewDao
}

//
fun getAppDatabase(context : Context) : AppDatabase{

    val migration_1_2 = object : Migration(1,2){
        override fun migrate(database: SupportSQLiteDatabase) {
            database
                .execSQL("CREATE TABLE `REVIEW` (`id` INTEGER, `review` TEXT," + "PRIMARY KEY(`id`)) ")
        }
    }

    return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "BookSearchDB"
    )
        .addMigrations(migration_1_2)
        .build()
}

