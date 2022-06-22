package com.sibs_test.sibs_test_felipe.di.module

import com.sibs_test.sibs_test_felipe.core.AppExecutors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExecutorsModule {

    @Provides
    @Singleton
    fun providesAppExecutors(): AppExecutors = AppExecutors()
}