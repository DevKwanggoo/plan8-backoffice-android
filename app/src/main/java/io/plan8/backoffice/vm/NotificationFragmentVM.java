package io.plan8.backoffice.vm;

import android.databinding.Bindable;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.adapter.BindingRecyclerViewAdapter;
import io.plan8.backoffice.model.api.Notification;
import io.plan8.backoffice.vm.item.NotificationItemVM;

/**
 * Created by SSozi on 2017. 12. 5..
 */

public class NotificationFragmentVM extends FragmentVM {
    private BindingRecyclerViewAdapter<Notification> adapter;
    private boolean empty;
    private List<Notification> notifications;

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
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getFragment().getContext(), LinearLayoutManager.VERTICAL, false);
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public void setData(List<Notification> data) {
        adapter.setData(data);
        if (this.notifications.size() <= 0) {
            setEmpty(true);
        } else  {
            setEmpty(false);
        }
    }

    public void addData(List<Notification> data) {
        this.notifications.addAll(data);
        adapter.addData(data);
        if (this.notifications.size() <= 0) {
            setEmpty(true);
        } else  {
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
}
