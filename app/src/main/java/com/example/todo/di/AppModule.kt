package com.example.todo.di

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.room.Room
import com.example.todo.data.room.TodoDatabase
import com.example.todo.util.Utils.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application): TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(db: TodoDatabase) = db.todoDao()

    fun notifyUser(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}