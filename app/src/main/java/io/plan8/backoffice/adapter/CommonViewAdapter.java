package io.plan8.backoffice.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeramen.roundedimageview.RoundedImageView;

/**
 * Created by chokwanghwan on 2017. 11. 29..
 */

public class CommonViewAdapter {
    @BindingAdapter("commonViewAdapter:glide")
    public static void glide(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load("http://i.imgur.com/DvpvklR.png").into(view);
    }

    @BindingAdapter("commonViewAdapter:glide")
    public static void glide(RoundedImageView view, String url) {
        if (url != null && !url.equals("")) {
            Glide.with(view.getContext())
                    .load(url)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(view);
        }
    }
}
