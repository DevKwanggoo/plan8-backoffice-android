package io.plan8.backoffice.vm;

import android.databinding.Bindable;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import io.plan8.backoffice.ApplicationManager;
import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.adapter.BindingRecyclerViewAdapter;
import io.plan8.backoffice.fragment.BaseFragment;
import io.plan8.backoffice.model.BaseModel;
import io.plan8.backoffice.model.api.Member;
import io.plan8.backoffice.model.api.User;
import io.plan8.backoffice.model.item.LabelItem;
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

    public MoreFragmentVM(BaseFragment fragment, @Nullable final Bundle savedInstanceState) {
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

    public void logout(final View view) {
        new MaterialDialog.Builder(getFragment().getContext())
                .title("정말로 로그아웃을 하시겠습니까?")
                .inputType(InputType.TYPE_CLASS_TEXT)
                .positiveText("확인")
                .negativeText("취소")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        ApplicationManager.getInstance().logout();
                        dialog.dismiss();
                    }
                })
                .show();
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
