package com.syapp.basecomponent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.i("${javaClass.simpleName} : onCreate")
        if (isEnableInject()) AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        Timber.i("${javaClass.simpleName} : onStart")
        super.onStart()
    }

    override fun onResume() {
        Timber.i("${javaClass.simpleName} : onResume")
        super.onResume()
    }

    override fun onPause() {
        Timber.i("${javaClass.simpleName} : onPause")
        super.onPause()
    }

    override fun onStop() {
        Timber.i("${javaClass.simpleName} : onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Timber.i("${javaClass.simpleName} : onDestroy")
        super.onDestroy()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    open fun isEnableInject(): Boolean = true
}