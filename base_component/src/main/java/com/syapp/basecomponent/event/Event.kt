package com.syapp.basecomponent.event

import androidx.lifecycle.Observer

/**
 * Created By lsy2014 on 2019-09-19
 */
class Event<T>(val content: T) {
    private var isHandled = false

    fun isHandleEvent(): Boolean {
        if (!isHandled) {
            isHandled = true
            return false
        }

        return true
    }
}

class EventObserver<T>(private val handleEvent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(t: Event<T>) {
        if (!t.isHandleEvent()) {
            handleEvent(t.content)
        }
    }
}