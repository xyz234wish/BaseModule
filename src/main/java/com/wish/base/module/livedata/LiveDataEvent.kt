package com.wish.base.module.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * @description: 可控制粘性非粘性监听事件
 * @Author: lihongzhi
 * @CreateDate: 12/1/20 12:31 PM
 */
open class LiveDataEvent<T> : LiveData<T> {
    companion object {
        const val START_VERSION = -1
    }

    constructor() : super() {
        mVersion = START_VERSION
    }

    constructor(value: T) : super(value) {
        mVersion = START_VERSION + 1
    }

    private var mVersion = START_VERSION

    override fun setValue(value: T) {
        mVersion++
        super.setValue(value)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, ObserverWrapper(observer))
    }

    fun observeSticky(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, observer)
    }

    private inner class ObserverWrapper<T>(val observer: Observer<in T>) : Observer<T> {
        private var mLastVersion = mVersion

        override fun onChanged(t: T) {
            if (mLastVersion < mVersion) {
                mLastVersion = mVersion
                observer.onChanged(t)
            }
        }
    }
}