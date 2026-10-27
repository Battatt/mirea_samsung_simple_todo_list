package com.example.simpletodolist.presentation.screen.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpletodolist.domain.usecase.ChangeTaskCompletedStatusUseCase
import com.example.simpletodolist.domain.usecase.GetAllTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val changeTaskCompletedStatusUseCase: ChangeTaskCompletedStatusUseCase,
) : ViewModel() {

    private val _state =
        MutableStateFlow(TodoListScreenState(emptyList()))
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getAllTasksUseCase().collect {
                _state.value = TodoListScreenState(it)
            }
        }
    }

    fun processCommand(command: TodoListScreenCommand) {
        viewModelScope.launch {
            when (command) {
                is TodoListScreenCommand.ChangeTaskCompletedStatus -> {
                    changeTaskCompletedStatusUseCase(command.taskId)
                }
            }
        }
    }

}