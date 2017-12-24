package io.plan8.backoffice.vm;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import io.plan8.backoffice.R;
import io.plan8.backoffice.activity.BaseActivity;
import io.plan8.backoffice.util.NetworkUtil;

/**
 * Created by SSozi on 2017. 12. 19..
 */

public class NetworkExceptionActivityVM extends ActivityVM {
    public NetworkExceptionActivityVM(BaseActivity activity, Bundle savedInstanceState) {
        super(activity, savedInstanceState);
    }

    public void retryRequest(View view){
        if (NetworkUtil.getInstance().checkInternetConnection(getActivity())){
            getActivity().finish();
            getActivity().overridePendingTransition(R.anim.pull_in_left_activity, R.anim.push_out_right_activity);
        } else {
            Toast.makeText(getActivity(), "네트워크에 연결되어 있지 않거나\n연결이 불안합니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
