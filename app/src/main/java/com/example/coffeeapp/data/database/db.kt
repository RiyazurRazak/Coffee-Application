package com.example.coffeeapp.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameTable
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.example.coffeeapp.data.dao.CartDao
import com.example.coffeeapp.data.dao.UserDao
import com.example.coffeeapp.data.model.local.Cart
import com.example.coffeeapp.data.model.local.User

@Database(version = 1, entities = [User::class,])
abstract class AppDatabase : RoomDatabase() {

//    @RenameTable(fromTableName = "coffee-with-kadhal", toTableName = "coffee")
//    class RenameTodoAutoMigration : AutoMigrationSpec

    abstract fun userDao() : UserDao

//    abstract fun cartDao(): CartDao
}
