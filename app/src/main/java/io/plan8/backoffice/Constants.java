package io.plan8.backoffice;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class Constants {
    //reservation status
    public static final String RESERVATION_STATUS_COMPLETE = "completed";
    public static final String RESERVATION_STATUS_INCOMPLETE = "incomplete";
    public static final String RESERVATION_STATUS_CANCELED = "canceled";

    //change Image code
    public static final int SELECT_IMAGE_CODE = 2000;
    public static final int PICK_IMAGE_CODE = 2001;
    public static final int SELECT_FILE_CODE = 2002;

    //activity Refresh code
    public static final int REFRESH_RESERVATION_FRAGMENT = 3000;

    //pagination count
    public static final int PAGINATION_ACTION_COUNT = 15;
    public static final int PAGINATION_NOTIFICATION_COUNT = 15;
    public static final int PAGINATION_RESERVATION_COUNT = 15;
}
