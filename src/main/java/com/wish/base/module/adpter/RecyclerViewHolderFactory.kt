package com.wish.base.module.adpter

import android.view.View

/**
 * @description:
 * @Author: lihongzhi
 * @CreateDate: 2021/8/19 19:21
 */
interface RecyclerViewHolderFactory<VH : BaseItemModule.BaseViewHolder> {
    fun onCreateViewHolder(view: View): VH
    fun getLayoutResId(): Int
}