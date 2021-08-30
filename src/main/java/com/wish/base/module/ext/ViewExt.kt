package com.wish.base.module.ext

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider

/**
 * @description:
 * @Author: lihongzhi
 * @CreateDate: 2021/8/24 17:33
 */

fun View.setRadius(radius: Float) {
    clipToOutline = true
    outlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View?, outline: Outline?) {
            outline?.setRoundRect(0, 0, view?.width ?: 0, view?.height ?: 0, radius)
        }
    }
}