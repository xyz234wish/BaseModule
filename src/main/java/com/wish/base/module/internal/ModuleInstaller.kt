package com.wish.base.module.internal

import android.app.Application
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.tencent.mmkv.MMKV
import com.wish.base.module.ext.ApplicationViewModelFactory

/**
 * @description: 用于初始化
 * @Author: lihongzhi
 * @CreateDate: 2021/5/12 5:00 下午
 */
internal class ModuleInstaller : ContentProvider() {

    override fun onCreate(): Boolean {
        val application = context!!.applicationContext as Application
        //viewModel
        ApplicationViewModelFactory.init(application)
        //mmkv
        MMKV.initialize(application)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }
}