package com.example.coffeeapp.data.repository

import com.example.coffeeapp.data.dao.CartDao
import com.example.coffeeapp.data.model.local.Cart
import javax.inject.Inject


//class CartRepository @Inject constructor(private val cartDao: CartDao) {
//
//    fun getCartDetails(): Cart? {
//        return cartDao.getCart()
//    }
//
//    fun insertOrderInCart(name: String, size: String, imgUrl: String, count: Int = 1, price: String): Unit {
//        val payload = Cart(name = name, size = size, imgUrl = imgUrl, count = count, price = price)
//        cartDao.addCart(payload)
//    }
//}