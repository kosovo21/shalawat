package com.example.shalawat.di

import com.example.shalawat.data.repository.ShalawatRepositoryImpl
import com.example.shalawat.domain.repository.ShalawatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindShalawatRepository(
        impl: ShalawatRepositoryImpl
    ): ShalawatRepository
}
