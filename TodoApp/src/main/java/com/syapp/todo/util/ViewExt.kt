package com.syapp.todo.util

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

/**
 * Created By lsy2014 on 2019-09-19
 */

fun <ViewT : View> RecyclerView.ViewHolder.bindView(@IdRes idRes: Int): Lazy<ViewT> {
    return lazy { itemView.findViewById<ViewT>(idRes) }
}

fun View?.showSnackbar(text: String) {
    Snackbar.make(this ?: return, text, Snackbar.LENGTH_SHORT).show()
}