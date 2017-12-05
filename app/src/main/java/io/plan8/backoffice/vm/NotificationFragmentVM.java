package io.plan8.backoffice.vm;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.adapter.BindingRecyclerViewAdapter;
import io.plan8.backoffice.model.item.NotificationItem;
import io.plan8.backoffice.vm.item.NotificationItemVM;

/**
 * Created by SSozi on 2017. 12. 5..
 */

public class NotificationFragmentVM extends FragmentVM {
    private BindingRecyclerViewAdapter adapter;

    public NotificationFragmentVM(Fragment fragment, final Bundle savedInstanceState) {
        super(fragment, savedInstanceState);

        adapter = new BindingRecyclerViewAdapter() {
            @Override
            protected int selectViewLayoutType(Object data) {
                return R.layout.item_notification;
            }

            @Override
            protected void bindVariables(ViewDataBinding binding, Object data) {
                binding.setVariable(BR.vm, new NotificationItemVM(getFragment(), savedInstanceState, (NotificationItem) data));
            }
        };
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getFragment().getContext(), LinearLayoutManager.VERTICAL, false);
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public void setData(List<Object> data) {
        if (null != adapter) adapter.setData(data);
    }
}
