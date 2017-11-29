package io.plan8.backoffice.vm;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class FragmentVM extends BaseVM {
    private Fragment fragment;
    public FragmentVM(Fragment fragment, Bundle savedInstanceState) {
        super(fragment.getContext(), savedInstanceState);
        this.fragment = fragment;
    }

    public Fragment getFragment() {
        return fragment;
    }
}
