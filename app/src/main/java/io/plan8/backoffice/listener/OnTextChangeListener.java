package io.plan8.backoffice.listener;

import android.widget.EditText;

/**
 * Created by chokwanghwan on 2017. 12. 1..
 */

public interface OnTextChangeListener {
    void onChange(EditText editText, CharSequence charSequence, int index, boolean isBackpress);
}
