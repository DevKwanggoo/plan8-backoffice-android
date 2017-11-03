package io.plan8.business

import android.app.Application

/**
 * Created by SSozi on 2017. 11. 2..
 */
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        //application 초기화
        if (BuildConfig.IS_RELEASE) {

        } else {

        }
    }
}