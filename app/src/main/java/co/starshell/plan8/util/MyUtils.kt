package co.starshell.plan8.util

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.net.URISyntaxException

/**
 * Created by SSozi on 2017. 11. 2..
 */
class MyUtils {
    fun dpToPx(dp: Double): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun pxToDp(px: Double): Int {
        return (px / Resources.getSystem().displayMetrics.density).toInt()
    }

    fun getDisplayMetrics(activity: Activity): DisplayMetrics {
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics
    }

    fun callApp(activity: Activity, url: String): Boolean {
        var intent: Intent? = null
        try {
            intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
        } catch (ex: URISyntaxException) {
            return false
        }

        try {
            var retval = true
            if (url.startsWith("intent")) {
                if (activity.packageManager.resolveActivity(intent, 0) == null) {
                    val packagename = intent!!.`package`
                    if (packagename != null) {
                        val uri = Uri.parse("market://search?q=pname:" + packagename)
                        intent = Intent(Intent.ACTION_VIEW, uri)
                        activity.startActivity(intent)
                        retval = true
                    }
                } else {
                    intent!!.addCategory(Intent.CATEGORY_BROWSABLE)
                    intent.component = null
                    try {
                        if (activity.startActivityIfNeeded(intent, -1)) {
                            retval = true
                        }
                    } catch (ex: ActivityNotFoundException) {
                        retval = false
                    }

                }
            } else {
                try {
                    activity.onBackPressed()
                    val uri = Uri.parse(url)
                    val i = Intent(Intent.ACTION_VIEW, uri)
                    activity.startActivity(i)
                } catch (e: Exception) {
                    e.printStackTrace()
                    if (url.startsWith("hdcardappcardansimclick://")) {
                        // 현대카드 intent구문, 강제 앱스토어 연결 처리
                        activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.hyundaicard.appcard")))
                    } else {
                        try {
                            activity.onBackPressed()
                            val baseIntent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                            val uri = Uri.parse("market://search?q=" + baseIntent.scheme!!)
                            val i = Intent(Intent.ACTION_VIEW, uri)
                            activity.startActivity(i)
                        } catch (e1: Exception) {
                            e.printStackTrace()
                        }

                    }
                }

                retval = true
            }
            return retval
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
            return false
        }

    }

    fun hideKeyboard(view: View) {
        val immhide = view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        immhide.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, 0)
    }
}