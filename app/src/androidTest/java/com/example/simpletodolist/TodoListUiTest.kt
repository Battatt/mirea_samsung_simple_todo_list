package com.example.simpletodolist

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class TodoListUiTest {
    @get:Rule
    val rule =
        createAndroidComposeRule<MainActivity>()

    @Test
    fun taskList_displaysAllThreeTasksFromJson() {
        rule
            .onNodeWithText("Купить молоко")
            .assertIsDisplayed()

        rule
            .onNodeWithText("Позвонить маме")
            .assertIsDisplayed()

        rule
            .onNodeWithText("Сделать ДЗ по Android")
            .assertIsDisplayed()
    }

    @Test
    fun clickingCheckbox_togglesItsState() {
        val checkbox = rule
            .onNodeWithTag("task_checkbox_1")
        checkbox.assertIsOff()

        checkbox.performClick()
        checkbox.assertIsOn()
    }

    @Test
    fun clickingTask_opensDetailsAndReturnsToList() {
        rule
            .onNodeWithText("Купить молоко")
            .performClick()

        rule
            .onNodeWithText("Task details")
            .assertIsDisplayed()

        rule
            .onNodeWithText("Купить молоко")
            .assertIsDisplayed()

        rule
            .onNodeWithText("2 литра, обезжиренное")
            .assertIsDisplayed()

        rule
            .onNodeWithContentDescription("Back")
            .performClick()


        rule
            .onNodeWithText("Позвонить маме")
            .assertIsDisplayed()

        rule
            .onNodeWithText("Сделать ДЗ по Android")
            .assertIsDisplayed()

        rule
            .onNodeWithText("Task details")
            .assertDoesNotExist()
    }

}