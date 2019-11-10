package com.syapp.componentbasicapp.testcomponent.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.syapp.basecomponent.BaseFragment
import com.syapp.componentbasicapp.R
import timber.log.Timber

/**
 * Created By lsy2014 on 2019-11-04
 */
class SecondFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.i("${javaClass.simpleName} : onCreateView")
        return inflater.inflate(R.layout.second_fragment, container, false)
    }

    override fun isEnableInject(): Boolean {
        return false
    }
}