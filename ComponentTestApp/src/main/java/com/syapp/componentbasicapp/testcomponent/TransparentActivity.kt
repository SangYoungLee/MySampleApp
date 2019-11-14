package com.syapp.componentbasicapp.testcomponent

import com.syapp.basecomponent.BaseActivity

class TransparentActivity : BaseActivity() {

    override fun isEnableInject(): Boolean {
        return false
    }
}