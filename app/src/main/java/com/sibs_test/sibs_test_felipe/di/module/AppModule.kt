package com.sibs_test.sibs_test_felipe.di.module

import android.content.Context
import com.sibs_test.sibs_test_felipe.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun contributeActivityInjector(@ApplicationContext app: Context): BaseApplication = app as BaseApplication
}