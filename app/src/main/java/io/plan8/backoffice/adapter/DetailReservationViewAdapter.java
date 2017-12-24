package io.plan8.backoffice.adapter;

import android.databinding.BindingAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.TextView;

import io.plan8.backoffice.R;
import io.plan8.backoffice.listener.OnTextChangeListener;
import io.plan8.backoffice.model.api.Action;
import io.plan8.backoffice.util.DateUtil;
import io.plan8.backoffice.util.ViewUtil;

/**
 * Created by SSozi on 2017. 11. 28..
 */

public class DetailReservationViewAdapter {
    @BindingAdapter("detailReservationViewAdapter:onTextChange")
    public static void onTextChange(final EditText view, final OnTextChangeListener listener) {
        view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int index, int eventCode, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int index, int eventCode, int i2) {
                boolean isBackPress = false;
                if (eventCode == 1) {
                    isBackPress = true;
                }
                listener.onChange(view, charSequence, index, isBackPress);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @BindingAdapter("detailReservationViewAdapter:activityText")
    public static void activityText(TextView view, Action action) {
        if (null == action) {
            return;
        }

        String result = "";
        String text = ViewUtil.getInstance().getActionItemText(action);
        String addedTime = DateUtil.getInstance().getChatTime(action.getAdded());

        result = text + " " + addedTime;
        final SpannableStringBuilder sb = new SpannableStringBuilder(result);
        sb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(view.getContext(), R.color.grayColor)), text.length() + 1, text.length() + 1 + addedTime.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        view.setText(sb);
    }
}