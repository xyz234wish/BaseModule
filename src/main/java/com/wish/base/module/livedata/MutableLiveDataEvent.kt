package com.wish.base.module.livedata

/**
 * @description: 可控制粘性非粘性监听事件
 * @Author: lihongzhi
 * @CreateDate: 12/1/20 3:50 PM
 */
class MutableLiveDataEvent<T> : LiveDataEvent<T> {

    constructor() : super()
    constructor(value: T) : super(value)

    public override fun setValue(value: T) {
        super.setValue(value)
    }

    public override fun postValue(value: T) {
        super.postValue(value)
    }
}