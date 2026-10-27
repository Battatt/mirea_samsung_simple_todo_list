package com.example.simpletodolist.presentation.navigation

import android.os.Bundle

sealed class Screen(
    val route: String
) {
    data object TodoList : Screen("todo_list")

    data object TaskDetails : Screen("task_details/{taskId}") {
        private const val TASK_ID = "taskId"

        fun createRoute(taskId: Int): String {
            return "task_details/$taskId"
        }

        fun getTaskId(arguments: Bundle?): Int {
            return requireNotNull(arguments?.getString(TASK_ID)).toInt()
        }
    }
}
