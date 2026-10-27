package com.example.simpletodolist.presentation.screen.todolist

sealed interface TodoListScreenCommand {
    data class ChangeTaskCompletedStatus(val taskId: Int): TodoListScreenCommand
}