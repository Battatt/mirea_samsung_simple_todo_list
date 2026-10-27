package com.example.simpletodolist.domain.usecase

import com.example.simpletodolist.domain.repository.TodoRepository
import jakarta.inject.Inject

class ChangeTaskCompletedStatusUseCase @Inject constructor(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(taskId: Int) {
        repository.changeTaskCompleted(taskId)
    }
}