package com.example.simpletodolist.presentation.screen.todolist

import com.example.simpletodolist.domain.model.Task

data class TodoListScreenState(
    val tasks: List<Task>
)