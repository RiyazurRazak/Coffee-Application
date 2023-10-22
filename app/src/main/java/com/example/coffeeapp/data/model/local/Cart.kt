package com.example.coffeeapp.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Cart(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo("Size")
    val size: String,

    @ColumnInfo("count")
    val count: Int = 1,

    @ColumnInfo("imgUrl")
    val imgUrl: String,

    @ColumnInfo("price")
    val price: String
)
