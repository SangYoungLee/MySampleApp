package com.syapp.todo.detailtask

import androidx.lifecycle.ViewModel
import com.syapp.todo.base.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailTaskModuleBinds {

    @IntoMap
    @Binds
    @ViewModelKey(DetailTaskViewModel::class)
    abstract fun bindsViewModelClass(viewModel: DetailTaskViewModel): ViewModel
}