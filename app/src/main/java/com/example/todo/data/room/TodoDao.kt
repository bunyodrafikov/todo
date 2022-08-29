package com.example.todo.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todo.models.Task

@Dao
interface TodoDao {
    @Insert()
    fun insertTask(todoModel: Task): Long

    @Query("Select * from Task where isFinished == 0")
    fun getTask(): LiveData<List<Task>>

    @Query("Delete from Task where id=:uid")
    fun deleteTask(uid: Long)
}