package io.plan8.backoffice.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class BlurView extends LinearLayout {
    private boolean isAlreadyInflated = false;

    public BlurView(Context context) {
        super(context);
    }

    public BlurView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BlurView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BlurView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public boolean isAlreadyInflated() {
        return isAlreadyInflated;
    }

    public void setAlreadyInflated(boolean alreadyInflated) {
        isAlreadyInflated = alreadyInflated;
    }
}
