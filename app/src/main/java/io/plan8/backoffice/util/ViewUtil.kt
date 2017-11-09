package io.plan8.backoffice.util

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by SSozi on 2017. 11. 2..
 */
object ViewUtil {
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

    fun hideKeyboard(view: View) {
        val immhide = view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        immhide.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, 0)
    }

    fun tintImage(bitmap: Bitmap, color: Int): Bitmap {
        val paint = Paint()
        paint.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
        val bitmapResult = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmapResult)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        return bitmapResult
    }

    fun getDrawableResource(context: Context, icon: String): Int {
        return context.resources.getIdentifier("ic_line_" + icon, "drawable", context.packageName)
    }
}