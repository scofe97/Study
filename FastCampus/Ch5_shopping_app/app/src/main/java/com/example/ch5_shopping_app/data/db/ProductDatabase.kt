package com.example.ch5_shopping_app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ch5_shopping_app.data.db.dao.ProductDao
import com.example.ch5_shopping_app.data.entity.product.ProductEntity
import com.example.ch5_shopping_app.utillity.DateConverter

@Database(
        entities = [ProductEntity::class],
        version = 1,
        exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class ProductDatabase: RoomDatabase() {

    companion object {
        const val DB_NAME = "ProductDataBase.db"
    }

    abstract fun productDao(): ProductDao

}