package com.syapp.todo.application

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.syapp.todo.repository.DefaultTaskRepository
import com.syapp.todo.repository.ITaskRepository
import com.syapp.todo.repository.datasource.DataSource
import com.syapp.todo.repository.datasource.LocalTaskDataSource
import com.syapp.todo.repository.datasource.RemoteTaskDataSource
import com.syapp.todo.repository.datasource.database.TasksDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(includes = [AppModuleBinds::class])
class AppModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RemoteDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LocalDataSource

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
    @LocalDataSource
    fun provideLocalTaskDataSource(roomDatabase: TasksDatabase): DataSource {
        return LocalTaskDataSource(roomDatabase.tasksDao())
    }

    @Singleton
    @Provides
    @RemoteDataSource
    fun provideRemoteTaskDataSource(): DataSource = RemoteTaskDataSource
}

@Module
abstract class AppModuleBinds {
    @Singleton
    @Binds
    abstract fun bindRepository(defaultTaskRepository: DefaultTaskRepository): ITaskRepository
}

object Migrations {
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE Tasks ADD isCompleted INTEGER NOT NULL DEFAULT 0")
        }
    }
}