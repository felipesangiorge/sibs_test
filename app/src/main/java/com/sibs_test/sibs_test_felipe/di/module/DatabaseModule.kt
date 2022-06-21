package com.sibs_test.sibs_test_felipe.di.module

import android.content.Context
import androidx.room.Room
import com.sibs_test.sibs_test_felipe.R
import com.sibs_test.sibs_test_felipe.database.AppDatabase
import com.sibs_test.sibs_test_felipe.database.dao.BooksDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesBookDao(
        appDatabase: AppDatabase
    ): BooksDao = appDatabase.bookDao()

    @Provides
    @Singleton
    fun providesAppDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        context.getString(R.string.app_database)
    ).fallbackToDestructiveMigration().build()
}