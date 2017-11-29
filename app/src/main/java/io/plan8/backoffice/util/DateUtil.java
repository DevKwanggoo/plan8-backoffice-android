package io.plan8.backoffice.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class DateUtil {
    private static volatile DateUtil instance = null;
    private String YYYYMD_FORMAT = "yyyy년 M월 d일";

    public static DateUtil getInstance() {
        if (null == instance) {
            synchronized (DateUtil.class) {
                instance = new DateUtil();
            }
        }
        return instance;
    }

    public String getCurrentDate() {
        return getFilteredDate(Calendar.getInstance().getTimeInMillis(), YYYYMD_FORMAT, null);
    }

    public String dateToYYYYMd(Date date) {
        return new SimpleDateFormat(YYYYMD_FORMAT).format(date);
    }

    private String getFilteredDate(Long milliSeconds, String format, Locale locale) {
        if (null == milliSeconds || milliSeconds.intValue() == 0) {
            return "";
        }

        SimpleDateFormat formatter = null;
        if (null != locale) {
            formatter = new SimpleDateFormat(format, locale);
        } else {
            formatter = new SimpleDateFormat(format);
        }
        Date date = new Date();
        return formatter.format(date);
    }
}
