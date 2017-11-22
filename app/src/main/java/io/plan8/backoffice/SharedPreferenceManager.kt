package io.plan8.backoffice

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by SSozi on 2017. 11. 21..
 */

class SharedPreferenceManager(private val context: Context) {

    var userToken: String
        get() {
            val pref = context.getSharedPreferences("plan8", Context.MODE_PRIVATE)
            return pref.getString("token", "")
        }
        set(token) {
            val pref = context.getSharedPreferences("plan8", Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString("token", token)
            editor.apply()
        }

    val isFirstInstalled: Boolean
        get() {
            val pref = context.getSharedPreferences("plan8", Context.MODE_PRIVATE)
            return !pref.getBoolean("isFirstInstalled", false)
        }

    fun removeToken() {
        val pref = context.getSharedPreferences("plan8", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.remove("token")
        editor.apply()
    }

    fun setIsFirstInstalled() {
        val pref = context.getSharedPreferences("plan8", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean("isFirstInstalled", true)
        editor.apply()
    }
}