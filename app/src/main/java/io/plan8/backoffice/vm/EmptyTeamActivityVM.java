package io.plan8.backoffice.vm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import io.plan8.backoffice.R;
import io.plan8.backoffice.SharedPreferenceManager;
import io.plan8.backoffice.activity.LoginActivity;

/**
 * Created by SSozi on 2017. 12. 19..
 */

public class EmptyTeamActivityVM extends ActivityVM {
    public EmptyTeamActivityVM(Activity activity, Bundle savedInstanceState) {
        super(activity, savedInstanceState);
    }

    public void teamLogout(View view){
        SharedPreferenceManager.getInstance().clearUserToken(getActivity());
        Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
        getActivity().startActivity(loginIntent);
        getActivity().finish();
        getActivity().overridePendingTransition(R.anim.pull_in_left_activity, R.anim.push_out_right_activity);
    }
}
