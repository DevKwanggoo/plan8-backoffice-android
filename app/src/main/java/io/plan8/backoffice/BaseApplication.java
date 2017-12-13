package io.plan8.backoffice;

import android.support.multidex.MultiDexApplication;

import com.google.firebase.FirebaseApp;
import com.onesignal.OneSignal;

import io.plan8.backoffice.util.OneSignalNotificationOpenedHandler;
import io.plan8.backoffice.util.OneSignalNotificationReciever;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class BaseApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationManager.getInstance().setContext(getApplicationContext());
        FirebaseApp.initializeApp(getApplicationContext());
//        Intercom.initialize(this, "android_sdk-5efb4215c714d457c400f5fe0297cd84e265c758", "pno474gz");

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .setNotificationReceivedHandler(new OneSignalNotificationReciever())
                .setNotificationOpenedHandler(new OneSignalNotificationOpenedHandler(getApplicationContext()))
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

//        OneSignal.setSubscription(true);
    }
}