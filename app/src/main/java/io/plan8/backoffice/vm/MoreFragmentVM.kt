package io.plan8.backoffice.vm

import android.os.Bundle
import android.support.v4.app.Fragment
import io.plan8.backoffice.model.item.TaskItem

/**
 * Created by chokwanghwan on 2017. 11. 9..
 */
open class MoreFragmentVM(fragment: Fragment
                          , savedInstanceState: Bundle?, taskItemList: List<TaskItem>) : FragmentVM(fragment, savedInstanceState) {

}