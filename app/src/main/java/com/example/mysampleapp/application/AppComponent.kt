package com.example.mysampleapp.application

import android.content.Context
import com.example.mysampleapp.activity.ActivityModuleBinds
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class,
    AppModuleBinds::class, ActivityModuleBinds::class])
interface AppComponent : AndroidInjector<TodoApplication> {

    @Component.Factory
    interface Builder {

        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}