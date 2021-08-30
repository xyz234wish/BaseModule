package com.wish.base.module.ext

import android.content.res.Resources
import android.util.TypedValue

/**
 * @description: 屏幕相关工具
 * @Author: lihongzhi
 * @CreateDate: 2021/5/12 4:11 下午
 */

/**
 * 获取屏幕分辨率宽度 *
 */
fun getScreenWidth(): Int {
    return Resources.getSystem().displayMetrics.widthPixels
}

/**
 * 获取屏幕分辨率高度 *
 */
fun getScreenHeight(): Int {
    return Resources.getSystem().displayMetrics.heightPixels
}

/**
 * 获取电池栏高度
 */
fun getStatusBarHeight(): Int {
    var result = 0
    val resourceId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = Resources.getSystem().getDimensionPixelSize(resourceId)
    }
    return result
}

/**
 * dp转换成px *
 */
val Float.dp: Float
    get() {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this,
            Resources.getSystem().displayMetrics
        )
    }

/**
 * dp转换成px *
 */
val Int.dp: Float
    get() {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        )
    }


/**
 * sp转换成px *
 */
val Float.sp: Float
    get() {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            this,
            Resources.getSystem().displayMetrics
        )
    }

/**
 * sp转换成px *
 */
val Int.sp: Float
    get() {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        )
    }