package com.example.smartstickynote.di

import android.content.Context
import com.example.smartstickynote.utils.AutoCategorizer
import com.example.smartstickynote.utils.EnhancedAutoCategorizer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    
    @Provides
    @Singleton
    fun provideAutoCategorizer(@ApplicationContext context: Context): AutoCategorizer {
        return AutoCategorizer(context)
    }
    
    @Provides
    @Singleton
    fun provideEnhancedAutoCategorizer(
        @ApplicationContext context: Context,
        basicCategorizer: AutoCategorizer
    ): EnhancedAutoCategorizer {
        return EnhancedAutoCategorizer(context, basicCategorizer)
    }
} 