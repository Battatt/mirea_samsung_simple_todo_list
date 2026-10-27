package com.example.simpletodolist.data.model

data class TaskDto(
    val id: Int,
    val title: String,
    val description: String,
    val isCompleted: Boolean
)