package io.plan8.backoffice.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.SharedPreferenceManager;
import io.plan8.backoffice.adapter.RestfulAdapter;
import io.plan8.backoffice.databinding.FragmentNotificationBinding;
import io.plan8.backoffice.listener.EndlessRecyclerOnScrollListener;
import io.plan8.backoffice.model.BaseModel;
import io.plan8.backoffice.model.api.Notification;
import io.plan8.backoffice.vm.NotificationFragmentVM;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SSozi on 2017. 12. 5..
 */

public class NotificationFragment extends BaseFragment {
    private FragmentNotificationBinding binding;
    private NotificationFragmentVM vm;
    private List<BaseModel> notifications;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_notification, container, false);
        vm = new NotificationFragmentVM(this, savedInstanceState);
        binding.setVariable(BR.vm, vm);
        binding.executePendingBindings();

        return binding.getRoot();
    }

    private void refreshNotificationList() {
        if (null == notifications) {
            notifications = new ArrayList<>();
        }
        Call<List<Notification>> getNotifications = RestfulAdapter.getInstance().getServiceApi().getNotifications("Bearer "+ SharedPreferenceManager.getInstance().getUserToken(getContext()));
        getNotifications.enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
                List<Notification> result = response.body();
                if (null != result) {
                    notifications.addAll(result);
                    List<BaseModel> tempList = new ArrayList<>();
                    tempList.addAll(result);
                    vm.addData(tempList);
                }
            }

            @Override
            public void onFailure(Call<List<Notification>> call, Throwable t) {
                Toast.makeText(getContext(), "알림 목록을 받아오는데 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.notificationRecycler.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore(int currentPage) {
//                refreshNotificationList();
                Log.e("test", "paging");
            }
        });
        refreshNotificationList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
