package io.plan8.backoffice.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class ViewUtil {
    private static volatile ViewUtil instance = null;

    public static ViewUtil getInstance() {
        if (null == instance) {
            synchronized (ViewUtil.class) {
                instance = new ViewUtil();
            }
        }

        return instance;
    }

    public int dpToPx(double dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public int pxToDp(double px) {
        return (int) (px/Resources.getSystem().getDisplayMetrics().density);
    }

    public DisplayMetrics getDisplayMetrics(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public void hideKeyboard(View view) {
        InputMethodManager immhide = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        immhide.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
    }
}
