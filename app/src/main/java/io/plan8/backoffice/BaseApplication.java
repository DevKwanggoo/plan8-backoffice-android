package io.plan8.backoffice;

import android.support.multidex.MultiDexApplication;

import com.google.firebase.FirebaseApp;

import io.intercom.android.sdk.Intercom;
import io.plan8.backoffice.adapter.RestfulAdapter;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class BaseApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(getApplicationContext());
        Intercom.initialize(this, "android_sdk-5efb4215c714d457c400f5fe0297cd84e265c758", "pno474gz");
        RestfulAdapter.build(getApplicationContext());
    }
}