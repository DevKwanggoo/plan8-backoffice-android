package io.plan8.backoffice.vm;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class ActivityVM extends BaseVM {
    private Activity activity;
    public ActivityVM(Activity activity, Bundle savedInstanceState) {
        super(activity.getApplicationContext(), savedInstanceState);
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }
}
