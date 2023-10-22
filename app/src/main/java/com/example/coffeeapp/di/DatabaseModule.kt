package com.example.coffeeapp.di

import androidx.room.Database
import com.example.coffeeapp.data.dao.CartDao
import com.example.coffeeapp.data.dao.UserDao
import com.example.coffeeapp.data.database.AppDatabase
import com.example.coffeeapp.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao {
        return db.userDao()
    }

//    @Provides
//    fun provideCartDao(db: AppDatabase): CartDao {
//        return db.cartDao()
//    }


}