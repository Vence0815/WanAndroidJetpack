package com.renrun.basedevelopjetpack.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.renrun.basedevelopjetpack.R
import com.renrun.basedevelopjetpack.contants.imgaUrlPre

/**
 * Created by vence on 2018/12/26 15:41
 * 邮箱 ：vence0815@foxmail.com
 */


@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String) {
    if (imageUrl.isNotEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .apply(
                RequestOptions().placeholder(R.drawable.loading_big)
                    .error(R.drawable.loading_big)
            )
            .into(view)
    }
}


