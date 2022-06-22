package com.sibs_test.sibs_test_felipe.di.module

import com.sibs_test.sibs_test_felipe.data.repository.BookStoreRepository
import com.sibs_test.sibs_test_felipe.data.repository.BookStoreRepository_Imp
import com.sibs_test.sibs_test_felipe.database.dao.BooksDao
import com.sibs_test.sibs_test_felipe.network.BookStoreService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideBookStoreRepository(
        bookStoreService: BookStoreService,
        booksDao: BooksDao
    ): BookStoreRepository = BookStoreRepository_Imp(bookStoreService, booksDao)
}