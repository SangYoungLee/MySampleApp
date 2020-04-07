package com.syapp.todo.base.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.syapp.basecomponent.viewmodel.BaseViewModel

abstract class BaseRecycleAdapter<T>(protected val viewModel: BaseViewModel? = null)
    : RecyclerView.Adapter<BaseViewHolder<T>>() {

    private val adapterItems: MutableList<BaseItem<T>> = arrayListOf()
    val rawItems: List<BaseItem<T>>
        get() = adapterItems

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val itemKind = ItemKind.getItemKind(viewType)
        val holder = itemKind?.holderCreator?.createHolder(parent, viewModel)
        return holder as? BaseViewHolder<T> ?: throw IllegalStateException("Holder Cast Error")
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.onBindData(adapterItems.getOrNull(position)?.data ?: return)
    }

    override fun onViewRecycled(holder: BaseViewHolder<T>) {
        holder.onRecycled()
    }

    override fun getItemCount(): Int {
        return adapterItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return adapterItems.getOrNull(position)?.viewType ?: 0
    }

    fun setItemList(list: List<T>?, viewType: Int) {
        removeAll()
        adapterItems.addAll(mapToBaseItem(list, viewType) ?: return)
    }

    fun addItem(item: T, viewType: Int) {
        adapterItems.add(mapToBaseItem(item, viewType))
    }

    fun removeAll() {
        adapterItems.clear()
    }

    protected fun mapToBaseItem(list: List<T>?, viewType: Int): List<BaseItem<T>>? {
        return list?.map { mapToBaseItem(it, viewType) }
    }

    protected fun mapToBaseItem(data: T, viewType: Int): BaseItem<T> {
        return BaseItem(viewType, data)
    }
}