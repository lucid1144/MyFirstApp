package com.example.mynote

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    val db = DatabaseProvider.getDatabase(getApplication())
    private val dao = db.taskDao()

    var taskList by mutableStateOf(emptyList<Task>())
    private set

    var selectedTask by mutableStateOf<Task?>(null)
    private set

    var query by mutableStateOf("")
    var description by mutableStateOf("")

    fun addTask(task: Task) {
        viewModelScope.launch {
            dao.insertTask(task)
            loadTasks()
        }
        taskList = taskList + task
    }

    fun deleteTask(task: Task) {
        if (selectedTask?.id == task.id) {
            selectedTask = null
        }
        viewModelScope.launch {
            dao.deleteTask(task)
            loadTasks()
        }
        taskList = taskList - task
    }

    fun selectTask(task: Task) {
        selectedTask = task
    }

    fun toggleTaskCompletion(task: Task) {
        val updatedTask = task.copy(isCompleted = !task.isCompleted)

        viewModelScope.launch {
            dao.updateTask(updatedTask)
            loadTasks()
        }
        taskList = taskList.map {
            if (it.id == task.id) updatedTask else it 
        }
        
        if (selectedTask?.id == task.id) {
            selectedTask = updatedTask
        }
    }
    fun loadTasks() {
        viewModelScope.launch {
            taskList = dao.getAllTasks()
        }

    }
}