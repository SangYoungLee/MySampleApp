package com.example.mysampleapp.application

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mysampleapp.repository.DefaultTaskRepository
import com.example.mysampleapp.repository.ITaskRepository
import com.example.mysampleapp.repository.LocalTaskDataSource
import com.example.mysampleapp.repository.database.TasksDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModuleBinds::class])
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): TasksDatabase {
        return Room.databaseBuilder(context, TasksDatabase::class.java,
            "Tasks.db")
            .addMigrations(Migrations.MIGRATION_1_2)
            .build()
    }

    @Singleton
    @Provides
    fun provideLocalTaskDatasource(roomDatabase: TasksDatabase): LocalTaskDataSource {
        return LocalTaskDataSource(roomDatabase.tasksDao())
    }
}

@Module
abstract class AppModuleBinds {
    @Singleton
    @Binds
    abstract fun provideRepository(defaultTaskRepository: DefaultTaskRepository): ITaskRepository
}

object Migrations {
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE Tasks ADD isCompleted INTEGER NOT NULL DEFAULT 0")
        }
    }
}