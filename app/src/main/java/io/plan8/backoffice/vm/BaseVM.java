package io.plan8.backoffice.vm;

import android.content.Context;
import android.databinding.BaseObservable;
import android.os.Bundle;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class BaseVM extends BaseObservable {
    private Context context;
    private Bundle savedInstanceState = null;

    public BaseVM(Context context, Bundle savedInstanceState) {
        this.context = context;
        this.savedInstanceState = savedInstanceState;
    }

    public BaseVM(Context context) {
        this.context = context;
    }
}
