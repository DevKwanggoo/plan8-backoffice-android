package io.plan8.backoffice

import android.support.multidex.MultiDexApplication
import io.plan8.backoffice.adapter.RestfulAdapter

/**
 * Created by SSozi on 2017. 11. 2..
 */
class BaseApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        RestfulAdapter.build(applicationContext)

        //application 초기화
        if (BuildConfig.IS_RELEASE) {

        } else {

        }
    }
}