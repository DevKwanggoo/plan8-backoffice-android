package io.plan8.backoffice.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetDialog;

/**
 * Created by SSozi on 2017. 11. 30..
 */

public class Plan8BottomSheetDialog extends BottomSheetDialog {
    public Plan8BottomSheetDialog(@NonNull Context context) {
        super(context);
    }

    public Plan8BottomSheetDialog(@NonNull Context context, @StyleRes int theme) {
        super(context, theme);
    }

    protected Plan8BottomSheetDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
