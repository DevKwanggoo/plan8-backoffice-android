package io.plan8.backoffice.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by chokwanghwan on 2017. 11. 5..
 */
object DateUtil {
    val YYYYMD_FORMAT: String = "yyyy년 M월 d일"
    fun getCurrentDate(): String {
        return dateFilter(Calendar.getInstance().timeInMillis, YYYYMD_FORMAT, null)
    }

    @SuppressLint("SimpleDateFormat")
    fun dateToYYYYMd(date: Date): String {
        return SimpleDateFormat(YYYYMD_FORMAT).format(date)
    }

    @SuppressLint("SimpleDateFormat")
    private fun dateFilter(milliSeconds: Long?, format: String, locale: Locale?): String {
        if (null == milliSeconds || milliSeconds.toInt() == 0) {
            return ""
        }
        val formatter: SimpleDateFormat = if (null != locale) {
            SimpleDateFormat(format, locale)
        } else {
            SimpleDateFormat(format)
        }
        val date = Date()

        return formatter.format(date)
    }
}