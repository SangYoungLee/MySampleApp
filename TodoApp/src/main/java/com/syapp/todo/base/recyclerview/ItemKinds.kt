package com.syapp.todo.base.recyclerview

import android.view.ViewGroup
import com.syapp.basecomponent.viewmodel.BaseViewModel
import com.syapp.todo.tasks.TaskHolder

/**
 * Created By lsy2014 on 07/04/2020
 */
class ItemKind private constructor(
    val holderCreator: HolderCreator,
    val code: Int = CodeGenerator.generateCode()
) {
    init {
        ITEM_KINDS_MAP[code] = this
    }

    companion object ItemKinds {
        private val ITEM_KINDS_MAP: MutableMap<Int, ItemKind> = hashMapOf()

        fun getItemKind(viewType: Int): ItemKind? = ITEM_KINDS_MAP[viewType]

        val TASK_LIST_ITEM: ItemKind by lazy { ItemKind(TaskHolder.Creator()) }
    }
}

interface HolderCreator {
    fun createHolder(parent: ViewGroup, viewModel: BaseViewModel?): BaseViewHolder
}