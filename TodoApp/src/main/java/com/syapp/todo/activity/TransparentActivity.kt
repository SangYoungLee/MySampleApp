package com.syapp.todo.activity

import com.syapp.basecomponent.BaseActivity

class TransparentActivity : BaseActivity() {

    override fun isEnableInject(): Boolean {
        return false
    }
}