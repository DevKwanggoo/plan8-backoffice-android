package io.plan8.backoffice.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.plan8.backoffice.ApplicationManager;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class DateUtil {
    private static volatile DateUtil instance = null;
    private String YYYYMD_FORMAT = "yyyy년 M월 d일";
    private String YYYYMD_API_FORMAT = "yyyy-MM-dd";

    public static DateUtil getInstance() {
        if (null == instance) {
            synchronized (DateUtil.class) {
                instance = new DateUtil();
            }
        }
        return instance;
    }

    public String getCurrentDate() {
        return getFilteredDateMiliseconds(Calendar.getInstance().getTimeInMillis(), YYYYMD_FORMAT);
    }

    public String getCurrnetDateAPIFormat(Date date) {
        return new SimpleDateFormat(YYYYMD_API_FORMAT, ApplicationManager.getInstance().getCurrentLocale()).format(date);
    }

    public String dateToYYYYMd(Date date) {
        return new SimpleDateFormat(YYYYMD_FORMAT, ApplicationManager.getInstance().getCurrentLocale()).format(date);
    }

    public String getReservationTime(String stringDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'", ApplicationManager.getInstance().getCurrentLocale());
        try {
            return new SimpleDateFormat("aa h:mm", ApplicationManager.getInstance().getCurrentLocale()).format(simpleDateFormat.parse(stringDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return stringDate;
    }

    public String getChatTime(String stringDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'", ApplicationManager.getInstance().getCurrentLocale());
        try {
            return new SimpleDateFormat("HH시 mm분 ss초", ApplicationManager.getInstance().getCurrentLocale()).format(simpleDateFormat.parse(stringDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return stringDate;
    }

    public String getReservationDate(String stringDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'", ApplicationManager.getInstance().getCurrentLocale());
        try {
            return new SimpleDateFormat("yyyy년 M월 d일", ApplicationManager.getInstance().getCurrentLocale()).format(simpleDateFormat.parse(stringDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return stringDate;
    }

    private String getFilteredDateMiliseconds(Long milliSeconds, String format) {
        if (null == milliSeconds || milliSeconds.intValue() == 0) {
            return "";
        }

        SimpleDateFormat formatter = null;
        formatter = new SimpleDateFormat(format, ApplicationManager.getInstance().getCurrentLocale());
        Date date = new Date();
        return formatter.format(date);
    }
}
