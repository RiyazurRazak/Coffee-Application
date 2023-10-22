package com.example.coffeeapp.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 101,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "mobile_number")
    val mobileNumber: String
)
