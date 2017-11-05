package io.plan8.business.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by chokwanghwan on 2017. 11. 5..
 */
object DateUtil {
    fun getCurrentDate(): String {
        return dateFilter(Calendar.getInstance().timeInMillis, "yyyy년 M월 d일", null)
    }

    fun dateToYYYYMd(date: Date): String {
        return SimpleDateFormat("yyyy년 M월 d일").format(date)
    }

    private fun dateFilter(milliSeconds: Long?, format: String, locale: Locale?): String {
        if (null == milliSeconds || milliSeconds.equals(0)) {
            return ""
        }
        val formatter: SimpleDateFormat
        if (null != locale) {
            formatter = SimpleDateFormat(format, locale)
        } else {
            formatter = SimpleDateFormat(format)
        }
        val date = Date()

        return formatter.format(date)
    }
}