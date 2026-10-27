package com.example.simpletodolist.di

import com.example.simpletodolist.data.repository.TodoRepositoryImpl
import com.example.simpletodolist.domain.repository.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindRepository(impl: TodoRepositoryImpl): TodoRepository
}