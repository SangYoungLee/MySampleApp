package com.syapp.componentbasicapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.syapp.basecomponent.BaseActivity
import com.syapp.componentbasicapp.fragments.FirstFragment
import com.syapp.componentbasicapp.fragments.SecondFragment
import com.syapp.componentbasicapp.fragments.ThirdFragment
import kotlinx.android.synthetic.main.main_activity.*
import timber.log.Timber

class MainActivity : BaseActivity(), View.OnClickListener {

    private var firstFragment: FirstFragment? = null
    private var secondFragment: SecondFragment? = null
    private var thirdFragment: ThirdFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        btn_first.setOnClickListener(this)
        btn_second.setOnClickListener(this)
        btn_third.setOnClickListener(this)

        et_input.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val str = s?.toString()
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id ?: return) {
            R.id.btn_first -> {
                if (firstFragment == null) {
                    firstFragment = FirstFragment()
                    supportFragmentManager.beginTransaction()
                        .add(R.id.container_contents, firstFragment!!)
                        .commitNow()
                } else {
                    supportFragmentManager.beginTransaction()
                        .remove(firstFragment!!)
                        .commitNow()

                    firstFragment = null
                }
            }

            R.id.btn_second -> {
                if (secondFragment == null) {
                    secondFragment = SecondFragment()
                    supportFragmentManager.beginTransaction()
                        .add(R.id.container_contents, secondFragment!!)
                        .commitNow()
                } else {
                    supportFragmentManager.beginTransaction()
                        .remove(secondFragment!!)
                        .commitNow()
                    secondFragment = null
                }
            }

            R.id.btn_third -> {
                if (thirdFragment == null) {
                    thirdFragment = ThirdFragment()
                    supportFragmentManager.beginTransaction()
                        .add(R.id.container_contents, thirdFragment!!)
                        .commitNow()
                } else {
                    supportFragmentManager.beginTransaction()
                        .remove(thirdFragment!!)
                        .commitNow()
                    thirdFragment = null
                }
            }
        }
    }

    override fun isEnableInject(): Boolean {
        return false
    }
}
