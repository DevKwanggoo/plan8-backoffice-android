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
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int index, int eventCode, int i2) {
                boolean isBackPress;
                if (eventCode == 0) {
                    isBackPress = false;
                } else {
                    isBackPress = true;
                }
                listener.onChange(view, charSequence, index, isBackPress);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}