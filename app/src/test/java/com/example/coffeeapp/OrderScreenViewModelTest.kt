package com.example.coffeeapp

import com.example.coffeeapp.data.utils.globalState.UserGlobalState
import com.example.coffeeapp.ui.screens.OrderScreen.OrderScreenViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Test


class OrderScreenViewModelTest {

    private val viewModel = OrderScreenViewModel(UserGlobalState())

    @Test
    fun whenViewModelLoaded() {
        val initialOrderCount = viewModel.orderCount
        assertEquals(1, initialOrderCount)
    }

    @Test
    fun increaseOrderCount() {
        viewModel.incrementOrderCount()
        assertEquals(2, viewModel.orderCount)
    }

    @Test
    fun decreaseOrderCount() {
        viewModel.decrementOrderCount()
        assertEquals(1, viewModel.orderCount)
    }

    @Test
    fun checkOrderCountNotBelowOne() {
        repeat(5) {
            viewModel.decrementOrderCount()
        }
        assertEquals(1, viewModel.orderCount)
    }

    @Test
    fun defaultSelectedSegament() {
        assertEquals("Deliver", viewModel.selectedSegment)
    }

}