package io.plan8.backoffice.adapter

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.net.Uri
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
    }
}