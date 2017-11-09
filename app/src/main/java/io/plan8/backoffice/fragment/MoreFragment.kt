package io.plan8.backoffice.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.plan8.backoffice.BR
import io.plan8.backoffice.R
import io.plan8.backoffice.databinding.FragmentMoreBinding
import io.plan8.backoffice.model.item.TaskItem
import io.plan8.backoffice.vm.MoreFragmentVM

/**
 * Created by chokwanghwan on 2017. 11. 9..
 */
class MoreFragment:BaseFragment() {
    var binding: FragmentMoreBinding? = null
    var vm: MoreFragmentVM? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val testData: MutableList<TaskItem> = mutableListOf<TaskItem>()
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_more, container, false)
        vm = MoreFragmentVM(this, savedInstanceState, testData)
        binding!!.setVariable(BR.vm, vm)
        binding!!.executePendingBindings()
        return binding!!.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        binding!!.unbind()
        super.onDestroy()
    }
}