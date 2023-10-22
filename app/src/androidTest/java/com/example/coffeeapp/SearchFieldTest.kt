package com.example.coffeeapp

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import com.example.coffeeapp.ui.core.SearchField
import com.example.coffeeapp.ui.theme.CoffeeAppTheme
import org.junit.Rule
import org.junit.Test

class SearchFieldTest {
    
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()
    
    @Test
    fun test(){
        rule.setContent {
            CoffeeAppTheme {
                var testFieldState by remember {
                    mutableStateOf("")
                }
                SearchField(value = testFieldState, onChange = {testFieldState = it}, placeholder = "Search" )
            }

        }
        rule.onNodeWithText("Search").assertExists("Placeholder not found")
        rule.onNodeWithContentDescription("filter icon").assertHasClickAction()
        rule.onNodeWithTag("search input field").performTextInput("coffee with kadhal")
        rule.onNodeWithTag("search input field").assert(hasText("coffee with kadhal"))
    }
            
}