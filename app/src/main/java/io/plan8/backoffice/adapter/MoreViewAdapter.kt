package io.plan8.backoffice.adapter

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.net.Uri
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.makeramen.roundedimageview.RoundedImageView

/**
 * Created by SSozi on 2017. 11. 10..
 */
class MoreViewAdapter {
    companion object {
        @BindingAdapter("moreViewAdapter:glide")
        @JvmStatic
        fun glide(view: RoundedImageView, uri: Uri?) {
            if (uri != null) {
                view.setImageURI(uri)
            }
        }

        @BindingAdapter("moreViewAdapter:glide")
        @JvmStatic
        fun glide(view: RoundedImageView, url: String?) {
            if (url != ""){
                Glide.with(view.context)
                        .load(url)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(view)
            }
        }
    }
}