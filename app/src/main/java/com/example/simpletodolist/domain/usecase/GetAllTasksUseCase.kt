package com.example.simpletodolist.domain.usecase

import com.example.simpletodolist.domain.model.Task
import com.example.simpletodolist.domain.repository.TodoRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetAllTasksUseCase @Inject constructor(
    private val repository: TodoRepository
) {
    operator fun invoke(): Flow<List<Task>> {
        return repository.getAllTasks()
    }
}