package com.syapp.basecomponent

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import timber.log.Timber
import javax.inject.Inject

abstract class BaseFragment : Fragment(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    init {
        retainInstance = true
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        Timber.i("${javaClass.simpleName} : onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("${javaClass.simpleName} : onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.i("${javaClass.simpleName} : onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("${javaClass.simpleName} : onViewCreated")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Timber.i("${javaClass.simpleName} : onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        Timber.i("${javaClass.simpleName} : onStart")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("${javaClass.simpleName} : onResume")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("${javaClass.simpleName} : onPause")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("${javaClass.simpleName} : onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.i("${javaClass.simpleName} : onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("${javaClass.simpleName} : onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Timber.i("${javaClass.simpleName} : onDetach")
    }

    override fun androidInjector(): AndroidInjector<Any>? {
        return androidInjector
    }
}