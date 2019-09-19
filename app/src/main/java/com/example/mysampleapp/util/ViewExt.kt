package com.example.mysampleapp.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * Created By lsy2014 on 2019-09-19
 */

fun View?.showSnackbar(text: String) {
    Snackbar.make(this ?: return, text, Snackbar.LENGTH_SHORT).show()
}