package com.wish.base.module.ext

import android.app.Application
import androidx.annotation.MainThread
import androidx.lifecycle.*
import kotlin.reflect.KClass

/**
 * @description: 全局单例ViewModelFactory，需要在application中初始化和销毁
 * @Author: lihongzhi
 * @CreateDate: 2020/10/12 3:52 PM
 */
object ApplicationViewModelFactory {
    private val applicationViewModelStore: ViewModelStore = ViewModelStore()
    private val userViewModelStore: ViewModelStore = ViewModelStore()
    private lateinit var factory: ViewModelProvider.Factory

    fun init(context: Application) {
        factory = ViewModelProvider.AndroidViewModelFactory.getInstance(context)
    }

    fun getApplicationViewModelStore(): ViewModelStore {
        return applicationViewModelStore
    }

    fun getUserViewModelStore(): ViewModelStore {
        return userViewModelStore
    }

    fun getViewModelFactory(): ViewModelProvider.Factory {
        return factory
    }

    fun <VM : ViewModel> getApplicationViewModel(viewModelClass: KClass<VM>): VM {
        return ViewModelProvider(applicationViewModelStore, factory).get(viewModelClass.java)
    }

    fun <VM : ViewModel> getUserViewModel(viewModelClass: KClass<VM>): VM {
        return ViewModelProvider(userViewModelStore, factory).get(viewModelClass.java)
    }
}

@MainThread
inline fun <reified VM : ViewModel> singleViewModels() = ViewModelLazy(
    VM::class,
    { ApplicationViewModelFactory.getApplicationViewModelStore() },
    { ApplicationViewModelFactory.getViewModelFactory() })

@MainThread
inline fun <reified VM : ViewModel> singleUserViewModels() = ViewModelLazy(
    VM::class,
    { ApplicationViewModelFactory.getUserViewModelStore() },
    { ApplicationViewModelFactory.getViewModelFactory() })