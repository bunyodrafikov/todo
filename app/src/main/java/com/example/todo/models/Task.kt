package com.example.todo.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    var title:String,
    var description:String?,
    var type: String,
    var date:Long?,
    var time:Long,
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
)