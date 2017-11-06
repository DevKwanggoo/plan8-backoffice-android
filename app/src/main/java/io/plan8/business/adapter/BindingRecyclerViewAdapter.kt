package io.plan8.business.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*

/**
 * Created by SSozi on 2017. 9. 15..
 */

abstract class BindingRecyclerViewAdapter<T> : RecyclerView.Adapter<BindingRecyclerViewAdapter.BindingViewHolder>() {
    var data: List<T>? = null
        set (data) {
            if (data != null)
                field = data
            else
                field = ArrayList()
            notifyDataSetChanged()
        }

    protected abstract fun selectViewLayoutType(data: T): Int

    protected abstract fun bindVariables(binding: ViewDataBinding, data: T)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, layoutId: Int): BindingRecyclerViewAdapter.BindingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(layoutId, parent, false)
        return BindingViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: BindingRecyclerViewAdapter.BindingViewHolder, position: Int) {
        bindVariables(viewHolder.binding, data!![position])
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun getItemViewType(position: Int): Int {
        return selectViewLayoutType(data!![position])
    }

    class BindingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ViewDataBinding

        init {
            this.binding = DataBindingUtil.bind(view)
        }
    }
}