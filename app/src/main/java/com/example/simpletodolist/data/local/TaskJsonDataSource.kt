package com.example.simpletodolist.data.local

import android.content.Context
import com.example.simpletodolist.data.model.TaskDto
import com.example.simpletodolist.data.model.TasksJson
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskJsonDataSource @Inject constructor(
    @param:ApplicationContext private val context: Context
) {
    private val gson = Gson()

    suspend fun readTasks(): List<TaskDto> {
        return withContext(Dispatchers.IO) {
            val json = context.assets
                .open("tasks.json")
                .bufferedReader(Charsets.UTF_8)
                .use { reader ->
                    reader.readText()
                }

            val result = gson.fromJson(json, TasksJson::class.java)

            result.data
        }
    }
}