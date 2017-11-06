package io.plan8.business.util

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by SSozi on 2017. 11. 2..
 */
object NetworkUtil {
    fun checkInternetConnection(context: Context): Boolean {

        val conManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return conManager.activeNetworkInfo != null
                && conManager.activeNetworkInfo.isAvailable
                && conManager.activeNetworkInfo.isConnected
    }
}