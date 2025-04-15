package com.verycool.frienddayapp.di

import com.verycool.frienddayapp.data.repository.GroupRepository
import com.verycool.frienddayapp.data.repository.GroupRepositoryImpl
import com.verycool.frienddayapp.data.repository.UserRepository
import com.verycool.frienddayapp.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideUserRepository(): UserRepository = UserRepositoryImpl()

    @Provides
    fun provideGroupRepository(): GroupRepository = GroupRepositoryImpl()
}