package com.example.todo.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.room.TodoDatabase
import com.example.todo.models.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(val db: TodoDatabase) : ViewModel() {
    fun saveTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            db.todoDao().insertTask(task)
        }
    }
}
