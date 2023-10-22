package com.example.coffeeapp.di

import com.example.coffeeapp.data.dao.CartDao
import com.example.coffeeapp.data.dao.UserDao
import com.example.coffeeapp.data.repository.UserRepository
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
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepository(userDao)
    }

//    @Provides
//    @Singleton
//    fun provideCartRepository(cartDao: CartDao): CartRepository {
//        return CartRepository(cartDao)
//    }
}