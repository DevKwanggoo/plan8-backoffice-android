package io.plan8.backoffice.vm;

import android.databinding.Bindable;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.SharedPreferenceManager;
import io.plan8.backoffice.adapter.BindingRecyclerViewAdapter;
import io.plan8.backoffice.adapter.RestfulAdapter;
import io.plan8.backoffice.fragment.NotificationFragment;
import io.plan8.backoffice.model.api.Notification;
import io.plan8.backoffice.vm.item.NotificationItemVM;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SSozi on 2017. 12. 5..
 */

public class NotificationFragmentVM extends FragmentVM {
    private BindingRecyclerViewAdapter<Notification> adapter;
    private boolean empty;
    private List<Notification> notifications;
    private boolean swipeFlag = true;

    public NotificationFragmentVM(Fragment fragment, final Bundle savedInstanceState) {
        super(fragment, savedInstanceState);
        notifications = new ArrayList<>();
        adapter = new BindingRecyclerViewAdapter<Notification>() {
            @Override
            protected int selectViewLayoutType(Notification data) {
                return R.layout.item_notification;
            }

            @Override
            protected void bindVariables(ViewDataBinding binding, Notification data) {
                binding.setVariable(BR.vm, new NotificationItemVM(getFragment(), savedInstanceState, data));
            }
        };

        adapter.setHasStableIds(true);
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getFragment().getContext(), LinearLayoutManager.VERTICAL, false);
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public void setData(List<Notification> data) {
        if (data.size() <= 0) {
            setEmpty(true);
        } else {
            setEmpty(false);
        }
        adapter.setData(data);
    }

    public void addData(List<Notification> data) {
        this.notifications.addAll(data);
        adapter.addData(data);
        if (this.notifications.size() <= 0) {
            setEmpty(true);
        } else {
            setEmpty(false);
        }
    }

    @Bindable
    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
        notifyPropertyChanged(BR.empty);
    }

    public void readAllNotifications(View view) {
        Map<String, Boolean> readMap = new HashMap<String, Boolean>();
        readMap.put("read", true);
        Call<Notification> readAllNotificationsCall = RestfulAdapter.getInstance().getServiceApi().readAllNotifications("Bearer " + SharedPreferenceManager.getInstance().getUserToken(getFragment().getContext()), readMap);
        readAllNotificationsCall.enqueue(new Callback<Notification>() {
            @Override
            public void onResponse(Call<Notification> call, Response<Notification> response) {
            }

            @Override
            public void onFailure(Call<Notification> call, Throwable t) {

            }
        });
        if (getFragment() instanceof NotificationFragment) {
            ((NotificationFragment) getFragment()).readAllNotifications();
        }
    }

    public boolean getNothing() {
        return false;
    }

    @Bindable
    public boolean getSwipeFlag(){
        return swipeFlag;
    }

    public void setSwipeFlag(boolean flag){
        swipeFlag = flag;
        notifyPropertyChanged(BR.swipeFlag);
    }
}
