package com.example.todo.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.models.Task


@Database(entities = [Task::class], version = 4)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}