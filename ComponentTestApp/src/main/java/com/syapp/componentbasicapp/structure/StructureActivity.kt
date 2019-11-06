package com.syapp.componentbasicapp.structure

import android.os.Bundle
import com.syapp.basecomponent.BaseActivity
import com.syapp.componentbasicapp.R

/**
 * Created By lsy2014 on 2019-11-05
 */
class StructureActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.structure_activity)
    }

    override fun isEnableInject(): Boolean = false
}