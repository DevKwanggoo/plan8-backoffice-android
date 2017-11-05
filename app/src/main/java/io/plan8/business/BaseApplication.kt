package io.plan8.business

import android.support.multidex.MultiDexApplication

/**
 * Created by SSozi on 2017. 11. 2..
 */
class BaseApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        //application 초기화
        if (BuildConfig.IS_RELEASE) {

        } else {

        }
    }
}