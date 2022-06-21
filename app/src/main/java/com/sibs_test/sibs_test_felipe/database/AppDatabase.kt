package com.sibs_test.sibs_test_felipe.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sibs_test.sibs_test_felipe.database.dao.BooksDao
import com.sibs_test.sibs_test_felipe.database.model_entity.BookEntity

@Database(
    entities = [
        BookEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BooksDao
}