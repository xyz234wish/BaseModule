package com.wish.base.module.coroutine

import android.util.Log
import com.wish.base.module.BuildConfig
import kotlinx.coroutines.CoroutineExceptionHandler
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.coroutines.CoroutineContext

/**
 * @description: 协程exception处理
 * @Author: lihongzhi
 * @CreateDate: 2021/5/24 7:13 下午
 */
object GlobalCoroutineExceptionHandler : CoroutineExceptionHandler {
    /**
     * A key of this coroutine context element.
     */
    override val key: CoroutineContext.Key<*>
        get() = CoroutineExceptionHandler

    /**
     * Handles uncaught [exception] in the given [context]. It is invoked
     * if coroutine has an uncaught exception.
     */
    override fun handleException(context: CoroutineContext, exception: Throwable) {
        if (BuildConfig.DEBUG) {
            Log.e("fatal", "handleException: ${getStackTraceString(exception)}" )
            throw exception
        }
    }

    private fun getStackTraceString(t: Throwable): String {
        // Don't replace this with Log.getStackTraceString() - it hides
        // UnknownHostException, which is not what we want.
        val sw = StringWriter(256)
        val pw = PrintWriter(sw, false)
        t.printStackTrace(pw)
        pw.flush()
        return sw.toString()
    }
}