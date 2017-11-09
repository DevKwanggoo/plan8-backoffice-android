package io.plan8.backoffice.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.plan8.backoffice.BR
import io.plan8.backoffice.Constants
import io.plan8.backoffice.R
import io.plan8.backoffice.databinding.FragmentMoreBinding
import io.plan8.backoffice.model.item.*
import io.plan8.backoffice.vm.MoreFragmentVM

/**
 * Created by chokwanghwan on 2017. 11. 9..
 */
class MoreFragment:BaseFragment() {
    var binding: FragmentMoreBinding? = null
    var vm: MoreFragmentVM? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val testData: MutableList<Any> = mutableListOf()
        //        이름, 전화번호, 주소, 예약일, 예약시간, 종료시간, 상품/이름, 추가 요청 사항, 내부 노트
        testData.add(MoreTitleItem("내 프로필"))
        testData.add(MoreProfileItem("이해찬", "+821020851422"))
        testData.add(MoreTitleItem("팀 선택"))
        testData.add(MoreTeamItem("픽스나우", "fixNow"))
        testData.add(MoreTeamItem("세탁특공대", "washSwat"))
        testData.add(EmptySpaceItem(0))
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