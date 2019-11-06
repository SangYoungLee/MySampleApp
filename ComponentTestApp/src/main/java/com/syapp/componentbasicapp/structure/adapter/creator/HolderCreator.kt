package com.syapp.componentbasicapp.structure.adapter.creator

import android.view.LayoutInflater
import android.view.ViewGroup
import com.syapp.componentbasicapp.structure.adapter.viewholder.ItemViewHolder

/**
 * Created By lsy2014 on 2019-11-05
 */
interface HolderCreator {

    fun newInstance(inflater: LayoutInflater, parent: ViewGroup): ItemViewHolder
}