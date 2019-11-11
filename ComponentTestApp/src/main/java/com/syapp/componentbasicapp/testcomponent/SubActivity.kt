package com.syapp.componentbasicapp.testcomponent

import android.app.PictureInPictureParams
import android.os.Build
import android.os.Bundle
import com.syapp.basecomponent.BaseActivity
import com.syapp.componentbasicapp.R
import kotlinx.android.synthetic.main.sub_activity.*

/**
 * Created By lsy2014 on 2019-11-11
 */
class SubActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sub_activity)

        btn_pip.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val param = PictureInPictureParams.Builder().build()
                enterPictureInPictureMode(param)
            }
        }
    }

    override fun isEnableInject(): Boolean {
        return false
    }
}