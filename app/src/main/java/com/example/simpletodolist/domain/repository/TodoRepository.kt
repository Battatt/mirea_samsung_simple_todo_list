package com.example.simpletodolist.domain.repository

import com.example.simpletodolist.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    fun getAllTasks(): Flow<List<Task>>

    suspend fun getTaskById(taskId: Int): Task

    suspend fun changeTaskCompleted(taskId: Int)
}