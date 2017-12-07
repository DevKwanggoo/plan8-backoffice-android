package io.plan8.backoffice;

import android.support.multidex.MultiDexApplication;

import com.google.firebase.FirebaseApp;
import com.onesignal.OneSignal;

import io.intercom.android.sdk.Intercom;
import io.plan8.backoffice.adapter.RestfulAdapter;
import io.plan8.backoffice.handler.OneSignalNotificationOpenedHandler;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class BaseApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationManager.getInstance().setContext(getApplicationContext());
        FirebaseApp.initializeApp(getApplicationContext());
        Intercom.initialize(this, "android_sdk-5efb4215c714d457c400f5fe0297cd84e265c758", "pno474gz");
        RestfulAdapter.build(getApplicationContext());
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationOpenedHandler(new OneSignalNotificationOpenedHandler(getApplicationContext()))
                .init();
    }
}