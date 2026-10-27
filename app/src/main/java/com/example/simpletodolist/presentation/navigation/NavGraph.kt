package com.example.simpletodolist.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simpletodolist.presentation.screen.taskdetails.TaskDetailsScreen
import com.example.simpletodolist.presentation.screen.todolist.TodoListScreen

@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.TodoList.route,
    ) {
        composable(route = Screen.TodoList.route) {
            TodoListScreen(
                onTaskClick = { task ->
                    navController.navigate(Screen.TaskDetails.createRoute(task.id))
                }
            )
        }

        composable(
            route = Screen.TaskDetails.route,
        ) { backStackEntry ->
            val taskId = Screen.TaskDetails.getTaskId(backStackEntry.arguments)

            TaskDetailsScreen(
                taskId = taskId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
