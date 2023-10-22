package com.example.coffeeapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.coffeeapp.data.model.local.Cart



@Dao
interface CartDao {

    @Insert
    fun addCart(payload: Cart)

    @Query("Select * from cart")
    fun getCart(): Cart?
}