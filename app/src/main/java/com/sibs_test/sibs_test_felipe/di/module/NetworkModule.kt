package com.sibs_test.sibs_test_felipe.di.module

import android.content.Context
import com.sibs_test.sibs_test_felipe.network.ApiRequest
import com.sibs_test.sibs_test_felipe.network.BookStoreService
import com.sibs_test.sibs_test_felipe.network.ServiceGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideBookStoreService(@ApplicationContext context: Context): BookStoreService {
        return BookStoreService(ServiceGenerator().serviceCreator(ApiRequest.BookStoreApi::class.java, context))
    }
}