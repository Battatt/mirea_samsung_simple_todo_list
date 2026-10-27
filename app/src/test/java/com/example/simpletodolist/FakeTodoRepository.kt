package com.example.simpletodolist

import com.example.simpletodolist.domain.model.Task
import com.example.simpletodolist.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeTodoRepository(
    private var tasks: List<Task>,
) : TodoRepository {

    override fun getAllTasks(): Flow<List<Task>> {
        return flowOf(tasks)
    }

    override suspend fun getTaskById(taskId: Int): Task {
        return tasks.find {task -> task.id == taskId }!!
    }

    override suspend fun changeTaskCompleted(taskId: Int) {
        tasks = tasks.map {
            if (it.id == taskId) {
                Task(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                    isCompleted = !it.isCompleted
                )
            } else {
                it
            }
        }
    }
}