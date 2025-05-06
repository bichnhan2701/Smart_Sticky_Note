package com.example.smartstickynote.di

import com.example.smartstickynote.data.remote.FirebaseNoteDataSource
import com.example.smartstickynote.data.remote.FirebaseNoteDataSourceImpl
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): DatabaseReference {
        return FirebaseDatabase
            .getInstance("https://smartstickynote-3a501-default-rtdb.asia-southeast1.firebasedatabase.app")
            .reference
    }

    @Provides
    @Singleton
    fun provideFirebaseNoteDataSource(
        db: DatabaseReference
    ): FirebaseNoteDataSource {
        return FirebaseNoteDataSourceImpl(db)
    }
}
