package io.plan8.backoffice.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by chokwanghwan on 2017. 11. 29..
 */

public class CommonViewAdapter {
    @BindingAdapter("commonViewAdapter:glide")
    public static void glide(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load("http://i.imgur.com/DvpvklR.png").into(view);
    }
}
