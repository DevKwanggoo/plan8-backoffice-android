package io.plan8.backoffice.adapter;

import android.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeramen.roundedimageview.RoundedImageView;

/**
 * Created by SSozi on 2017. 11. 28..
 */

public class MoreViewAdapter {
    @BindingAdapter("moreViewAdapter:glide")
    public static void glide(RoundedImageView view, String url) {
        if (url != null && !url.equals("")){
            Glide.with(view.getContext())
                    .load(url)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(view);
        }
    }
}
