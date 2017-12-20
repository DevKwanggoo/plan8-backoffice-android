package io.plan8.backoffice.vm;

import android.content.Intent;
import android.databinding.Bindable;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.SharedPreferenceManager;
import io.plan8.backoffice.activity.LoginActivity;
import io.plan8.backoffice.adapter.BindingRecyclerViewAdapter;
import io.plan8.backoffice.model.BaseModel;
import io.plan8.backoffice.model.api.Member;
import io.plan8.backoffice.model.api.User;
import io.plan8.backoffice.model.item.LabelItem;
import io.plan8.backoffice.util.PushManager;
import io.plan8.backoffice.vm.item.EmptySpaceItemVM;
import io.plan8.backoffice.vm.item.LabelItemVM;
import io.plan8.backoffice.vm.item.MoreProfileItemVM;
import io.plan8.backoffice.vm.item.MoreTeamItemVM;

/**
 * Created by SSozi on 2017. 11. 28..
 */

public class MoreFragmentVM extends FragmentVM {
    private BindingRecyclerViewAdapter<BaseModel> adapter;
    private List<BaseModel> moreItemList = new ArrayList<>();
    private boolean completedLoading;
    private boolean swipeFlag = true;

    public MoreFragmentVM(Fragment fragment, @Nullable final Bundle savedInstanceState) {
        super(fragment, savedInstanceState);

        adapter = new BindingRecyclerViewAdapter<BaseModel>() {
            @Override
            protected int selectViewLayoutType(BaseModel data) {
                if (data instanceof LabelItem) {
                    return R.layout.item_more_title;
                } else if (data instanceof User) {
                    return R.layout.item_more_profile;
                } else if (data instanceof Member) {
                    return R.layout.item_more_team;
                } else {
                    return R.layout.item_empty_space;
                }
            }

            @Override
            protected void bindVariables(ViewDataBinding binding, BaseModel data) {
                if (data instanceof LabelItem) {
                    binding.setVariable(BR.vm, new LabelItemVM(getFragment(), savedInstanceState, (LabelItem) data));
                } else if (data instanceof User) {
                    binding.setVariable(BR.vm, new MoreProfileItemVM(getFragment(), savedInstanceState, (User) data));
                } else if (data instanceof Member) {
                    binding.setVariable(BR.vm, new MoreTeamItemVM(getFragment(), savedInstanceState, (Member) data));
                } else {
                    binding.setVariable(BR.vm, new EmptySpaceItemVM(getFragment(), savedInstanceState));
                }
            }
        };

        setData(moreItemList);
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getFragment().getContext(), LinearLayoutManager.VERTICAL, false);
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public void setData(List<BaseModel> data) {
        if (null != adapter) adapter.setData(data);
    }

    public void logout(View view) {
        SharedPreferenceManager.getInstance().clearUserToken(getFragment().getContext());
        Intent loginIntent = new Intent(getFragment().getActivity(), LoginActivity.class);
        getFragment().getActivity().startActivity(loginIntent);
        getFragment().getActivity().finish();
        getFragment().getActivity().overridePendingTransition(R.anim.pull_in_left_activity, R.anim.push_out_right_activity);
        new PushManager().clearTag();
    }

    @Bindable
    public boolean isCompletedLoading() {
        return completedLoading;
    }

    public void setCompletedLoading(boolean compltedLoading) {
        this.completedLoading = compltedLoading;
        notifyPropertyChanged(BR.completedLoading);
    }

    @Bindable
    public boolean getSwipeFlag() {
        return swipeFlag;
    }

    public void setSwipeFlag(boolean flag) {
        swipeFlag = flag;
        notifyPropertyChanged(BR.swipeFlag);
    }
}
