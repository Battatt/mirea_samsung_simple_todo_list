package com.example.simpletodolist.domain.usecase

import com.example.simpletodolist.domain.model.Task
import com.example.simpletodolist.domain.repository.TodoRepository
import jakarta.inject.Inject

class GetTaskByIdUseCase @Inject constructor(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(taskId: Int): Task {
        return repository.getTaskById(taskId)
    }
}