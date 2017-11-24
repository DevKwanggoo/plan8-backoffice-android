package io.plan8.backoffice

import android.support.multidex.MultiDexApplication
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.provider.FirebaseInitProvider
import io.intercom.android.sdk.Intercom
import io.plan8.backoffice.adapter.RestfulAdapter

/**
 * Created by SSozi on 2017. 11. 2..
 */
class BaseApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(applicationContext)

        RestfulAdapter.build(applicationContext)
        Intercom.initialize(this, "android_sdk-5efb4215c714d457c400f5fe0297cd84e265c758", "pno474gz")

        //application 초기화
        if (BuildConfig.IS_RELEASE) {

        } else {

        }
    }
}