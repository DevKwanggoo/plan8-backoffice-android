package io.plan8.backoffice.vm;

import android.os.Bundle;

import io.plan8.backoffice.fragment.BaseFragment;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class FragmentVM extends BaseVM {
    private BaseFragment fragment;
    public FragmentVM(BaseFragment fragment, Bundle savedInstanceState) {
        super(fragment.getContext(), savedInstanceState);
        this.fragment = fragment;
    }

    public BaseFragment getFragment() {
        return fragment;
    }
}
