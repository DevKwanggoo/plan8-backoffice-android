package io.plan8.backoffice.vm;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.plan8.backoffice.activity.LoginActivity;
import io.plan8.backoffice.adapter.BindingRecyclerViewAdapter;
import io.plan8.backoffice.databinding.FragmentMoreBinding;

/**
 * Created by SSozi on 2017. 11. 28..
 */

public class MoreFragmentVM {
    private FragmentMoreBinding binding;
    private BindingRecyclerViewAdapter adapter;
    private List<Object> moreItemList = new ArrayList<>();

    public MoreFragmentVM(Fragment fragment, @Nullable Bundle savedInstanceState) {
        super(fragment, savedInstanceState);

        adapter = new BindingRecyclerViewAdapter() {
            @Override
            protected int selectViewLayoutType(Object data) {
                if (data instanceof MoreTitleItem){
                    return R.layout.item_more_title;
                } else if (data instanceof MoreProfileItem) {
                    return R.layout.item_more_profile;
                } else if (data instanceof MoreTeamItem) {
                    return R.layout.item_more_team;
                } else {
                    return R.layout.item_empty_space;
                }

            }

            @Override
            protected void bindVariables(ViewDataBinding binding, Object data) {
                if (data instanceof MoreTitleItem){
                    binding.setVariable(BR.vm, new MoreTitleItemVM(getFragment(), getSavedInstanceState()), data);
                } else if (data instanceof MoreProfileItem) {
                    binding.setVariable(BR.vm, new MoreProfileItem(getFragment(), getSavedInstanceState()), data);
                } else if (data instanceof MoreTeamItem) {
                    binding.setVariable(BR.vm, new MoreTeamItem(getFragment(), getSavedInstanceState()), data);
                } else {
                    binding.setVariable(BR.vm, new EmptySpaceItem(getFragment(), getSavedInstanceState()));
                }
            }
        };

        setData(moreItemList);
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getFragment().getContext(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return true;
            }

            @Override
            public boolean canScrollVertically() {
                return true;
            }
        };
        return layoutManager;
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public void setData(List<Object> data) {
        if (null != adapter) adapter.setData(data);
    }

    public void logout(View view){
        SharedPreferenceManager(getFragment().getContext()).removeToken();
        Intent loginIntent = new Intent(getFragment().getActivity(), LoginActivity.class);
        getFragment().getActivity().startActivity(loginIntent);
        getFragment().getActivity().finish();
        getFragment().getActivity().overridePendingTransition(R.anim.pull_in_left_activity, R.anim.push_out_right_activity)
    }
}
