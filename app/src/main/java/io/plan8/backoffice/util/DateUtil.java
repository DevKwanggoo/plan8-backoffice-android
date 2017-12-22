package io.plan8.backoffice.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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

    public String getCurrentDateAPIFormpat() {
        return new SimpleDateFormat(YYYYMD_API_FORMAT, ApplicationManager.getInstance().getCurrentLocale()).format(Calendar.getInstance().getTimeInMillis());
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
        int minute = (int) ((getCurrentDateLongFormat() + Long.parseLong(ApplicationManager.getInstance().getServerTimeOffset()) - getTZFormatToMiliseconds(stringDate)) / 60000.0f);

        if (minute < 60) {
            if (minute <= 0) {
                return "지금";
            } else {
                return minute + "분 전";
            }
        } else if (minute >= 60 && minute < 1440) {
            return minute / 60 + "시간 전";
        } else {
            if (minute < 2880) {
                return "어제";
            } else {
                return getReservationDate(stringDate);
            }
        }
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

    public Long getTZFormatToMiliseconds(String tzDate) {
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA);
        originalFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Long convertDate = null;
        try {
            Date date = originalFormat.parse(tzDate);
            convertDate = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertDate;
    }

    public Long getCurrentDateLongFormat() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTimeInMillis();
    }

    public String getMilisecondsToTZFormat(Long tzDate) {
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA);
        originalFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String convertDate = originalFormat.format(new Date(tzDate));

        return convertDate;
    }

    public String sumTime(String date, Long millisecond){
        Long prevTime = getTZFormatToMiliseconds(date);
        prevTime = prevTime + millisecond;
        return getMilisecondsToTZFormat(prevTime);
    }
}
