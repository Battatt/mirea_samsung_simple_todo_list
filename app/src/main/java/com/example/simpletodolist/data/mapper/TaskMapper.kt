package com.example.simpletodolist.data.mapper

import com.example.simpletodolist.data.model.TaskDto
import com.example.simpletodolist.domain.model.Task


fun TaskDto.toDomainModel(): Task {
    return Task(
        id = this.id,
        title = this.title,
        description = this.description,
        isCompleted = this.isCompleted
    )
}

fun List<TaskDto>.toDomainModels(): List<Task> {
    return this.map { taskDto ->
        taskDto.toDomainModel()
    }
}