package com.example.coffeeapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.coffeeapp.data.model.local.User


@Dao
interface UserDao {

    @Insert
    fun addUser(payload: User)

    @Query("Select * from user where id = 101")
    fun getUser(): User?
}