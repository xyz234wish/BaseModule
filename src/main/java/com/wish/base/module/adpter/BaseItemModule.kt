package com.wish.base.module.adpter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @description:
 * @Author: lihongzhi
 * @CreateDate: 2021/8/19 19:09
 */
abstract class BaseItemModule<VH : BaseItemModule.BaseViewHolder> {
    internal val factory: RecyclerViewHolderFactory<VH> by lazy {
        getViewHolderFactory()
    }

    @Suppress("UNCHECKED_CAST")
    internal fun bind(holder: BaseViewHolder) {
        val clickableView = holder.getClickableView()
        val longClickableView = holder.getLongClickableView()
        clickableView.forEach { view ->
            view.setOnClickListener {
                onClick(it, holder as VH)
            }
        }
        longClickableView.forEach { view ->
            view.setOnLongClickListener { v ->
                onLongClick(v, holder as VH)
            }
        }
        onBind(holder as VH)
    }

    @Suppress("UNCHECKED_CAST")
    internal fun bind(holder: BaseViewHolder, payloads: MutableList<Any>) {
        onBind(holder as VH, payloads)
    }

    @Suppress("UNCHECKED_CAST")
    internal fun viewRecycled(holder: BaseViewHolder) {
        unBind(holder as VH)
    }

    @Suppress("UNCHECKED_CAST")
    internal fun viewAttachedToWindow(holder: BaseViewHolder) {
        onViewAttachedToWindow(holder as VH)
    }

    @Suppress("UNCHECKED_CAST")
    internal fun viewDetachedFromWindow(holder: BaseViewHolder) {
        onViewDetachedFromWindow(holder as VH)
    }

    abstract fun onBind(holder: VH)

    abstract fun onClick(view: View, holder: VH)

    open fun onLongClick(view: View, holder: VH): Boolean = false

    open fun onBind(holder: VH, payloads: MutableList<Any>) {
        bind(holder)
    }

    open fun unBind(holder: VH) {
    }

    open fun onViewAttachedToWindow(holder: VH) {
    }

    open fun onViewDetachedFromWindow(holder: VH) {
    }

    open fun getViewType(): Int {
        return factory.getLayoutResId()
    }

    protected abstract fun getViewHolderFactory(): RecyclerViewHolderFactory<VH>

    abstract class BaseViewHolder(viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {
        abstract fun getClickableView(): List<View>
        fun getLongClickableView(): List<View> = emptyList()
    }
}