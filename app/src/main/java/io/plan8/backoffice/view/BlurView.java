package io.plan8.backoffice.view;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * Created by chokwanghwan on 2017. 11. 28..
 */

public class BlurView extends LinearLayout {
    private boolean isAlreadyInflated = false;
    public BlurView(Context context) {
        super(context);
    }

    public boolean isAlreadyInflated() {
        return isAlreadyInflated;
    }

    public void setAlreadyInflated(boolean alreadyInflated) {
        isAlreadyInflated = alreadyInflated;
    }
}
