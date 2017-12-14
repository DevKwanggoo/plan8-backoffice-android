package io.plan8.backoffice.adapter;

import android.databinding.BindingAdapter;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeramen.roundedimageview.RoundedImageView;
import com.twitter.Extractor;

import java.util.List;

import io.plan8.backoffice.Constants;
import io.plan8.backoffice.R;

/**
 * Created by chokwanghwan on 2017. 11. 29..
 */

public class CommonViewAdapter {
    @BindingAdapter("commonViewAdapter:glide")
    public static void glide(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }

    @BindingAdapter("commonViewAdapter:glide")
    public static void glide(RoundedImageView view, String url) {
        if (url != null && !url.equals("")) {
            Glide.with(view.getContext())
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(view);
        }
    }

    @BindingAdapter("commonViewAdapter:selection")
    public static void selection(final EditText view, int index) {
        view.post(new Runnable() {
            @Override
            public void run() {
                view.setSelection(view.getText().length());
            }
        });
    }

    @BindingAdapter("commonViewAdapter:mention")
    public static void mention(TextView view, String text) {
        if (null == text) {
            return;
        }

        List<Extractor.Entity> mentionList = new Extractor().extractMentionsOrListsWithIndices(text);

        final SpannableStringBuilder sb = new SpannableStringBuilder(text);
        for (Extractor.Entity e : mentionList) {
            sb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(view.getContext(), R.color.colorPrimary)), e.getStart(), e.getEnd(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
        view.setText(sb);
    }

    @BindingAdapter("commonViewAdapter:setReservationStatus")
    public static void setReservationStatus(RelativeLayout view, String reservationStatus) {
        view.setBackgroundResource(R.drawable.circle);
        GradientDrawable bgShape = (GradientDrawable) view.getBackground();

        if (reservationStatus.equals(Constants.RESERVATION_STATUS_COMPLETE)) {
            bgShape.setColor(ContextCompat.getColor(view.getContext(), R.color.reservationComplte));
        } else if (reservationStatus.equals(Constants.RESERVATION_STATUS_INCOMPLETE)) {
            bgShape.setColor(ContextCompat.getColor(view.getContext(), R.color.reservationIncomplte));
        } else if (reservationStatus.equals(Constants.RESERVATION_STATUS_CANCELED)) {
            bgShape.setColor(ContextCompat.getColor(view.getContext(), R.color.reservationCanceled));
        } else {
            bgShape.setColor(ContextCompat.getColor(view.getContext(), R.color.transparent));
        }
    }

    @BindingAdapter("commonViewAdapter:initRecyclerView")
    public static void initRecyclerView(RecyclerView view, Boolean nothing) {
        view.setHasFixedSize(true);
        view.setItemAnimator(null);
    }
}