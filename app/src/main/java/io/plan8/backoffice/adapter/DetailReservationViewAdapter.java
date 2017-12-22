package io.plan8.backoffice.adapter;

import android.databinding.BindingAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import io.plan8.backoffice.listener.OnTextChangeListener;

/**
 * Created by SSozi on 2017. 11. 28..
 */

public class DetailReservationViewAdapter {
    @BindingAdapter("detailReservationViewAdapter:onTextChange")
    public static void onTextChange(final EditText view, final OnTextChangeListener listener) {
        view.addTextChangedListener(new TextWatcher() {
            int prevIndex = 0;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int index, int eventCode, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int index, int eventCode, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                int currentIndex;
                boolean isBackpress;
                if (editable.length() <= 0) {
                    currentIndex = 0;
                } else {
                    currentIndex = editable.length()-1;
                }

                if (prevIndex > currentIndex) {
                    isBackpress = true;
                } else {
                    isBackpress = false;
                }

                listener.onChange(view, editable.toString(), editable.length(), isBackpress);
                prevIndex = editable.length();
            }
        });
    }
}