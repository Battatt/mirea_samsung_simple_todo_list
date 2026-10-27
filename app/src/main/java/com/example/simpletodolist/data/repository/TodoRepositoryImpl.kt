package com.example.simpletodolist.data.repository

import com.example.simpletodolist.data.local.TaskJsonDataSource
import com.example.simpletodolist.data.mapper.toDomainModels
import com.example.simpletodolist.domain.model.Task
import com.example.simpletodolist.domain.repository.TodoRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import java.io.IOException

class TodoRepositoryImpl @Inject constructor(
    private val dataSource: TaskJsonDataSource
) : TodoRepository {
    private val tasks: MutableStateFlow<List<Task>> = MutableStateFlow(emptyList())
    private var isLoaded: Boolean = false

    private suspend fun ensureLoaded(){
        if (!isLoaded) {
            tasks.value = dataSource.readTasks().toDomainModels()
            isLoaded = true
        }
    }

    override fun getAllTasks(): Flow<List<Task>> {
        return flow {
            ensureLoaded()
            emitAll(tasks.asStateFlow())
        }
    }

    override suspend fun getTaskById(taskId: Int): Task {
        ensureLoaded()
        return tasks.value.find { it.id == taskId }
            ?: throw IOException("Task with taskId $taskId not found")
    }

    override suspend fun changeTaskCompleted(taskId: Int) {
        tasks.update { currentTasks ->
            val index = currentTasks.indexOfFirst { it.id == taskId }
            if (index == -1) {
                throw IOException("Task with taskId $taskId not found")
            }

            val updatedList = currentTasks.toMutableList()
            val oldTask = updatedList[index]
            updatedList[index] = oldTask.copy(isCompleted = !oldTask.isCompleted)

            updatedList
        }
    }
}