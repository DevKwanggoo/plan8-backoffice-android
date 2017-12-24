package io.plan8.backoffice.vm;

import android.os.Bundle;

import io.plan8.backoffice.activity.BaseActivity;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class ActivityVM extends BaseVM {
    private BaseActivity activity;
    public ActivityVM(BaseActivity activity, Bundle savedInstanceState) {
        super(activity.getApplicationContext(), savedInstanceState);
        this.activity = activity;
    }

    public BaseActivity getActivity() {
        return activity;
    }
}
