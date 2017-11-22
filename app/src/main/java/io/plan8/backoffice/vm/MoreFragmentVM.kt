package io.plan8.backoffice.vm

import android.content.Intent
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import io.plan8.backoffice.BR
import io.plan8.backoffice.R
import io.plan8.backoffice.SharedPreferenceManager
import io.plan8.backoffice.activity.LoginActivity
import io.plan8.backoffice.adapter.BindingRecyclerViewAdapter
import io.plan8.backoffice.model.item.TaskItem
import io.plan8.backoffice.model.item.MoreProfileItem
import io.plan8.backoffice.model.item.MoreTeamItem
import io.plan8.backoffice.model.item.MoreTitleItem
import io.plan8.backoffice.vm.item.*

/**
 * Created by chokwanghwan on 2017. 11. 9..
 */
open class MoreFragmentVM(val fragment: Fragment
                          , savedInstanceState: Bundle?, moreItemList: List<Any>) : FragmentVM(fragment, savedInstanceState) {
    var adapter: BindingRecyclerViewAdapter<Any>
    var data: List<Any>? = null
    lateinit var moreProfileVM: MoreProfileItemVM

    init {
        adapter = object : BindingRecyclerViewAdapter<Any>() {

            override fun selectViewLayoutType(data: Any): Int {
                return when (data) {
                    is MoreTitleItem -> R.layout.item_more_title
                    is MoreProfileItem -> R.layout.item_more_profile
                    is MoreTeamItem -> R.layout.item_more_team
                    else -> R.layout.item_empty_space
                }
            }

            override fun bindVariables(binding: ViewDataBinding, data: Any) {
                when (data) {
                    is MoreTitleItem -> {
                        val moreTitleItemVM = MoreTitleItemVM(fragment, savedInstanceState, data)
                        binding.setVariable(BR.vm, moreTitleItemVM)
                    }
                    is MoreProfileItem -> {
                        val moreProfileItemVM = MoreProfileItemVM(fragment, savedInstanceState, data)
                        binding.setVariable(BR.vm, moreProfileItemVM)
                        moreProfileVM = moreProfileItemVM
                    }
                    is MoreTeamItem -> {
                        val moreTeamItemVM = MoreTeamItemVM(fragment, savedInstanceState, data)
                        binding.setVariable(BR.vm, moreTeamItemVM)
                    }
                    else -> {
                        val emptySpaceItemVM = EmptySpaceItemVM(fragment, savedInstanceState)
                        binding.setVariable(BR.vm, emptySpaceItemVM)
                    }
                }
            }
        }

        setDatas(moreItemList)
    }

    fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }

    fun setDatas(data: List<Any>?) {
        this.data = data
        adapter.data = this.data
    }

    fun logout(view: View){
        SharedPreferenceManager(fragment.context).removeToken()
        val loginIntent = Intent(fragment.activity, LoginActivity::class.java)
        fragment.activity.startActivity(loginIntent)
        fragment.activity.finish()
        fragment.activity.overridePendingTransition(R.anim.pull_in_left_activity, R.anim.push_out_right_activity)
    }
}