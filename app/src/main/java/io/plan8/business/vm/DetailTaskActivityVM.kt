package io.plan8.business.vm

import android.os.Bundle
import io.plan8.business.activity.DetailTaskActivity
import io.plan8.business.model.item.TaskItem

/**
 * Created by chokwanghwan on 2017. 11. 5..
 */
open class DetailTaskActivityVM(activity: DetailTaskActivity, savedInstanceState: Bundle?, taskItem: TaskItem) : ActivityVM(activity, savedInstanceState) {
//    var data: List<Listable>? = null
//    var adapter: BindingRecyclerViewAdapter<*>? = null
//    fun getLayoutManager(): RecyclerView.LayoutManager {
//        return LinearLayoutManager(context)
//    }

//    init {
//        adapter = object : BindingRecyclerViewAdapter<TaskItem>() {
//            override fun selectViewLayoutType(taskItem: TaskItem?): Int {
////                return R.layout.item_task
//            }
//
//            override fun bindVariables(binding: ViewDataBinding?, taskItem: TaskItem?) {
////                val taskItemVM = TaskItemVM(activity, savedInstanceState, taskItem!!)
////                binding!!.setVariable(BR.vm, taskItemVM)
//            }
//        }
//        setDatas(data)
//    }

//    fun setDatas(data: List<Listable>?) {
//        this.data = data
//        adapter!!.data = this.data
//    }
}