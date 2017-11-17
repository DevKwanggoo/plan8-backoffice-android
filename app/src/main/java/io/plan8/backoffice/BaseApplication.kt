package io.plan8.backoffice

import android.support.multidex.MultiDexApplication
import io.intercom.android.sdk.Intercom
import io.plan8.backoffice.adapter.RestfulAdapter

/**
 * Created by SSozi on 2017. 11. 2..
 */
class BaseApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        RestfulAdapter.build(applicationContext)
        Intercom.initialize(this, "android_sdk-5efb4215c714d457c400f5fe0297cd84e265c758", "pno474gz")

        //application 초기화
        if (BuildConfig.IS_RELEASE) {

        } else {

        }
    }
}