package com.wish.base.module.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * @description: 在不做任何处理的情况下，协程作用域内的异常是不会打印详细信息的，因此通过此方法封装统一的异常处理。
 * @Author: lihongzhi
 * @CreateDate: 2021/5/24 7:15 下午
 */
fun CoroutineScope.launchX(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) = launch(context + GlobalCoroutineExceptionHandler, start, block)