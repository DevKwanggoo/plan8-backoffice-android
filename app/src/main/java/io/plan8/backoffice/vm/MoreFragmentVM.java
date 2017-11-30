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

import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.SharedPreferenceManager;
import io.plan8.backoffice.activity.LoginActivity;
import io.plan8.backoffice.activity.MainActivity;
import io.plan8.backoffice.adapter.BindingRecyclerViewAdapter;
import io.plan8.backoffice.databinding.FragmentMoreBinding;
import io.plan8.backoffice.model.BaseModel;
import io.plan8.backoffice.model.item.LabelItem;
import io.plan8.backoffice.model.item.MoreProfileItem;
import io.plan8.backoffice.model.item.MoreTeamItem;
import io.plan8.backoffice.vm.item.EmptySpaceItemVM;
import io.plan8.backoffice.vm.item.LabelItemVM;
import io.plan8.backoffice.vm.item.MoreProfileItemVM;
import io.plan8.backoffice.vm.item.MoreTeamItemVM;

/**
 * Created by SSozi on 2017. 11. 28..
 */

public class MoreFragmentVM extends FragmentVM {
    private List<Object> datas;
    private FragmentMoreBinding binding;
    private BindingRecyclerViewAdapter adapter;
    private List<Object> moreItemList = new ArrayList<>();

    public MoreFragmentVM(Fragment fragment, @Nullable final Bundle savedInstanceState, List<Object> datas) {
        super(fragment, savedInstanceState);
        this.datas = datas;

        adapter = new BindingRecyclerViewAdapter() {
            @Override
            protected int selectViewLayoutType(Object data) {
                if (data instanceof LabelItem) {
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
                if (data instanceof LabelItem) {
                    binding.setVariable(BR.vm, new LabelItemVM(getFragment(), savedInstanceState, (LabelItem) data));
                } else if (data instanceof MoreProfileItem) {
                    binding.setVariable(BR.vm, new MoreProfileItemVM(getFragment(), savedInstanceState, (MoreProfileItem) data));
                } else if (data instanceof MoreTeamItem) {
                    binding.setVariable(BR.vm, new MoreTeamItemVM(getFragment(), savedInstanceState, (MoreTeamItem) data));
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

    public void setData(List<Object> data) {
        if (null != adapter) adapter.setData(data);
    }

    public void logout(View view) {
        SharedPreferenceManager.getInstance().clearUserToken(getFragment().getContext());
        Intent loginIntent = new Intent(getFragment().getActivity(), LoginActivity.class);
        getFragment().getActivity().startActivity(loginIntent);
        getFragment().getActivity().finish();
        getFragment().getActivity().overridePendingTransition(R.anim.pull_in_left_activity, R.anim.push_out_right_activity);
//        ((MainActivity) getFragment().getActivity()).setEmptyFlag(false);
    }
}
