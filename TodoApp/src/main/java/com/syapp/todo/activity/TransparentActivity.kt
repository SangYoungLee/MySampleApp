package com.syapp.todo.activity

import com.syapp.todo.base.BaseActivity

class TransparentActivity : BaseActivity() {

    override fun isEnableInject(): Boolean {
        return false
    }
}