package io.plan8.backoffice.adapter;

import android.databinding.BindingAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.makeramen.roundedimageview.RoundedImageView;

import io.plan8.backoffice.R;
import io.plan8.backoffice.listener.OnTextChangeListener;

/**
 * Created by SSozi on 2017. 11. 28..
 */

public class DetailTaskViewAdapter {
    @BindingAdapter("detailTaskViewAdapter:isTaskStatus")
    public static void isTaskStatus(RoundedImageView view, String taskStatus) {
        if (taskStatus.equals("완료")) {
            view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.taskStatusBlue));
        } else {
            view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.taskStatusRed));
        }
    }

    @BindingAdapter("detailTaskViewAdapter:onTextChange")
    public static void onTextChange(final EditText view, final OnTextChangeListener listener) {
        view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                listener.onChange(view);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}