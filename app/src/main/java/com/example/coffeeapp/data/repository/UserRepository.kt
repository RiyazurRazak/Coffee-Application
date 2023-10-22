package com.example.coffeeapp.data.repository

import com.example.coffeeapp.data.dao.UserDao
import com.example.coffeeapp.data.model.local.User
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao)  {

    suspend fun getUser(): User? {
        return userDao.getUser()
    }

    suspend fun insertUser(name: String, mobileNumber: String): User {
        val user = userDao.getUser()
        if (user == null) {
            val payload = User(name = name, mobileNumber = mobileNumber)
            userDao.addUser(payload = payload)
            return payload
        } else {
            return user
        }
    }
}