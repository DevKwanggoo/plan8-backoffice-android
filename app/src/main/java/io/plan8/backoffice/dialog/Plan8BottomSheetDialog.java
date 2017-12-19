package io.plan8.backoffice.dialog;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import io.plan8.backoffice.R;
import io.plan8.backoffice.util.ViewUtil;

/**
 * Created by SSozi on 2017. 11. 30..
 */

public class Plan8BottomSheetDialog extends BottomSheetDialog {
    private RelativeLayout firstItem;
    private AppCompatImageView firstIcon;
    private TextView firstTitle;
    private RelativeLayout secondItem;
    private AppCompatImageView secondIcon;
    private TextView secondTitle;
    private RelativeLayout thirdItemContainer;
    private RelativeLayout thirdItem;
    private AppCompatImageView thirdIcon;
    private TextView thirdTitle;


    public Plan8BottomSheetDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public Plan8BottomSheetDialog(@NonNull Context context, @StyleRes int theme) {
        super(context, theme);
        init();
    }

    protected Plan8BottomSheetDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        setContentView(R.layout.bottom_sheet_layout);
        firstItem = findViewById(R.id.bottomSheetFirstItem);
        firstIcon = findViewById(R.id.bottomSheetFirstIcon);
        firstTitle = findViewById(R.id.bottomSheetFirstTitle);

        secondItem = findViewById(R.id.bottomSheetSecondItem);
        secondIcon = findViewById(R.id.bottomSheetSecondIcon);
        secondTitle = findViewById(R.id.bottomSheetSecondTitle);

        thirdItemContainer = findViewById(R.id.bottomSheetThirdItemContainer);
        thirdItem = findViewById(R.id.bottomSheetThirdItem);
        thirdIcon = findViewById(R.id.bottomSheetThirdIcon);
        thirdTitle = findViewById(R.id.bottomSheetThirdTitle);
    }

    public RelativeLayout getFirstItem() {
        return firstItem;
    }

    public RelativeLayout getSecondItem() {
        return secondItem;
    }

    public RelativeLayout getThirdItem() {
        return thirdItem;
    }

    public void hideIcon() {
        firstIcon.setVisibility(View.GONE);
        secondIcon.setVisibility(View.GONE);
    }

    public void setFirstItem(String title) {
        firstIcon.setVisibility(View.GONE);
        firstTitle.setText(title);
    }

    public void setFirstCircleItem(int colorId, String title) {
        firstIcon.getLayoutParams().height = ViewUtil.getInstance().dpToPx(12);
        firstIcon.getLayoutParams().width = ViewUtil.getInstance().dpToPx(12);
        firstIcon.requestLayout();
        firstIcon.setBackgroundResource(R.drawable.circle);
        GradientDrawable bgShape = (GradientDrawable) firstIcon.getBackground();
        bgShape.setColor(ContextCompat.getColor(firstIcon.getContext(), colorId));
        firstTitle.setText(title);
    }

    public void setFirstItem(int iconResId, String title) {
        firstIcon.setImageResource(iconResId);
        firstIcon.setColorFilter(ContextCompat.getColor(getContext(), R.color.grayColor));
        firstTitle.setText(title);
    }

    public void setSecondItem(String title) {
        secondIcon.setVisibility(View.GONE);
        secondTitle.setText(title);
    }

    public void setSecondCircleItem(int colorId, String title) {
        secondIcon.getLayoutParams().height = ViewUtil.getInstance().dpToPx(12);
        secondIcon.getLayoutParams().width = ViewUtil.getInstance().dpToPx(12);
        secondIcon.requestLayout();
        secondIcon.setBackgroundResource(R.drawable.circle);
        GradientDrawable bgShape = (GradientDrawable) secondIcon.getBackground();
        bgShape.setColor(ContextCompat.getColor(secondIcon.getContext(), colorId));
        secondTitle.setText(title);
    }

    public void setSecondItem(int iconResId, String title) {
        secondIcon.setImageResource(iconResId);
        secondIcon.setColorFilter(ContextCompat.getColor(getContext(), R.color.grayColor));
        secondTitle.setText(title);
    }

    public void setThirdItem(String title) {
        thirdItemContainer.setVisibility(View.VISIBLE);
        thirdIcon.setVisibility(View.GONE);
        thirdTitle.setText(title);
    }

    public void setThirdCircleItem(int colorId, String title) {
        thirdIcon.getLayoutParams().height = ViewUtil.getInstance().dpToPx(12);
        thirdIcon.getLayoutParams().width = ViewUtil.getInstance().dpToPx(12);
        thirdIcon.requestLayout();
        thirdItemContainer.setVisibility(View.VISIBLE);
        thirdIcon.setBackgroundResource(R.drawable.circle);
        GradientDrawable bgShape = (GradientDrawable) thirdIcon.getBackground();
        bgShape.setColor(ContextCompat.getColor(thirdIcon.getContext(), colorId));
        thirdTitle.setText(title);
    }

    public void setThirdItem(int iconResId, String title){
        thirdItemContainer.setVisibility(View.VISIBLE);
        thirdIcon.setImageResource(iconResId);
        thirdIcon.setColorFilter(ContextCompat.getColor(getContext(), R.color.grayColor));
        thirdTitle.setText(title);
    }
}
