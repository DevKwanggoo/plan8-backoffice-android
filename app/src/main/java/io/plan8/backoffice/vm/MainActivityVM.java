package io.plan8.backoffice.vm;

import android.app.Activity;
import android.content.Intent;
import android.databinding.Bindable;
import android.os.Bundle;
import android.view.View;

import io.plan8.backoffice.BR;
import io.plan8.backoffice.R;
import io.plan8.backoffice.SharedPreferenceManager;
import io.plan8.backoffice.activity.LoginActivity;
import io.plan8.backoffice.activity.MainActivity;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class MainActivityVM extends ActivityVM {
    private MainActivity activity;

    public MainActivityVM(Activity activity, Bundle savedInstanceState) {
        super(activity, savedInstanceState);
        this.activity = (MainActivity) activity;
    }
}
