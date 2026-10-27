package com.example.simpletodolist

import com.example.simpletodolist.domain.model.Task
import com.example.simpletodolist.domain.usecase.ChangeTaskCompletedStatusUseCase
import com.example.simpletodolist.domain.usecase.GetAllTasksUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UseCaseTest {
    val expectedTasks = listOf(
        Task(
            id = 1,
            title = "Первая задача",
            description = "Описание первой",
            isCompleted = false,
        ),
        Task(
            id = 2,
            title = "Вторая задача",
            description = "Описание второй",
            isCompleted = true,
        ),
        Task(
            id = 3,
            title = "Третья задача",
            description = "Описание третьей",
            isCompleted = false,
        ),
    )

    val repository = FakeTodoRepository(expectedTasks)

    @Test
    fun getAllTaskUseCaseCalledWithoutChangingInitialData_ReturnsInitialData() = runBlocking {
        val useCase = GetAllTasksUseCase(repository)

        val actualTasks = useCase().first()

        assertEquals(expectedTasks.size, actualTasks.size)
        assertEquals(expectedTasks, actualTasks)
    }

    @Test
    fun changeTaskIsCompletedStatus_ChangesTaskIsCompletedStatus() = runBlocking {
        val useCase = ChangeTaskCompletedStatusUseCase(repository)
        val taskBefore = repository.getTaskById(1)

        useCase(taskBefore.id)
        val taskAfter = repository.getTaskById(1)

        assertEquals(!taskBefore.isCompleted, taskAfter.isCompleted)
    }
}