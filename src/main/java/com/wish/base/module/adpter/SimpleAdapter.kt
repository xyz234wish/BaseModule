package com.wish.base.module.adpter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @description: RecyclerView 简单适配器
 * @Author: lihongzhi
 * @CreateDate: 2021/8/19 18:18
 */
class SimpleAdapter : RecyclerView.Adapter<BaseItemModule.BaseViewHolder>() {
    /**
     * 数据集合
     */
    private val dataList = mutableListOf<BaseItemModule<*>>()
    private val factoryMap = mutableMapOf<Int, RecyclerViewHolderFactory<*>>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseItemModule.BaseViewHolder {
        val factory = factoryMap[viewType]
        return if (factory == null) {
            object : BaseItemModule.BaseViewHolder(View(parent.context)) {
                override fun getClickableView(): List<View> {
                    return emptyList()
                }
            }
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(factory.getLayoutResId(), parent, false)
            factory.onCreateViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: BaseItemModule.BaseViewHolder, position: Int) {
        dataList[position].bind(holder)
    }

    override fun onBindViewHolder(
        holder: BaseItemModule.BaseViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        dataList[position].bind(holder, payloads)
    }

    override fun onViewRecycled(holder: BaseItemModule.BaseViewHolder) {
        super.onViewRecycled(holder)
        dataList[holder.bindingAdapterPosition].viewRecycled(holder)
    }

    override fun onViewAttachedToWindow(holder: BaseItemModule.BaseViewHolder) {
        super.onViewAttachedToWindow(holder)
        dataList[holder.bindingAdapterPosition].viewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: BaseItemModule.BaseViewHolder) {
        super.onViewDetachedFromWindow(holder)
        dataList[holder.bindingAdapterPosition].viewDetachedFromWindow(holder)
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        val viewType = dataList[position].getViewType()
        if (factoryMap[viewType] == null) {
            factoryMap[viewType] = dataList[position].factory
        }
        return viewType
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataList(list: List<BaseItemModule<*>>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    fun insertData(position: Int, itemModule: BaseItemModule<*>) {
        dataList.add(position, itemModule)
        notifyItemInserted(position)
    }

    fun insertData(position: Int, list: List<BaseItemModule<*>>) {
        dataList.addAll(position, list)
        notifyItemRangeInserted(position, list.size)
    }

    fun removeData(position: Int) {
        dataList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getData(position: Int): BaseItemModule<*> {
        return dataList[position]
    }
}