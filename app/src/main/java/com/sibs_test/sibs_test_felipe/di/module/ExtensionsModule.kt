package com.sibs_test.sibs_test_felipe.di.module

import android.content.Context
import com.sibs_test.sibs_test_felipe.utils.TinyDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExtensionsModule {

    @Provides
    @Singleton
    fun provideTinyDB(@ApplicationContext context: Context): TinyDB = TinyDB(context)
}