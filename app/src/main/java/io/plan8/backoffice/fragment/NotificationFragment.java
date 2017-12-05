package io.plan8.backoffice.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.databinding.FragmentNotificationBinding;
import io.plan8.backoffice.model.item.NotificationItem;
import io.plan8.backoffice.vm.NotificationFragmentVM;

/**
 * Created by SSozi on 2017. 12. 5..
 */

public class NotificationFragment extends BaseFragment {
    private FragmentNotificationBinding binding;
    private NotificationFragmentVM vm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_notification, container, false);
        vm = new NotificationFragmentVM(this, savedInstanceState);
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();

        List<Object> testData = new ArrayList<>();
        testData.add(new NotificationItem("http://i.imgur.com/DvpvklR.png", "조영규님이 상태를 업데이트 하였습니다.", "어제"));
        testData.add(new NotificationItem("http://i.imgur.com/DvpvklR.png", "조광환님이 댓글을 남겼습니다 \"이거는 그거입니다\". 그게 뭔데요 이사람아 저사람아", "어제"));
        testData.add(new NotificationItem("http://i.imgur.com/DvpvklR.png", "이해찬님이 상태를 업데이트 하였습니다.", "2017년 3월 5일"));
        vm.setData(testData);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
