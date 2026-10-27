package com.example.simpletodolist.presentation.screen.taskdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpletodolist.domain.usecase.GetTaskByIdUseCase
import com.example.simpletodolist.domain.model.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TaskDetailsViewModel @Inject constructor(
    private val getTaskByIdUseCase: GetTaskByIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<Task?>(null)
    val state = _state.asStateFlow()

    fun loadTask(taskId: Int) {
        if (_state.value?.id == taskId) return

        viewModelScope.launch {
            _state.value = getTaskByIdUseCase(taskId)
        }
    }
}
