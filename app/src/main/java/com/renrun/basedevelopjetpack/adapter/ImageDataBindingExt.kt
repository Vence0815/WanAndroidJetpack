package com.renrun.basedevelopjetpack.adapter.databingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.orhanobut.logger.Logger
import com.renrun.basedevelopjetpack.contants.imgaUrlPre

/**
 * Created by vence on 2018/12/26 15:41
 * 邮箱 ：vence0815@foxmail.com
 */
@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    val url = imgaUrlPre + imageUrl
//    Logger.e("imgUrl---" + url)
    if (!url.isEmpty()) {
        Glide.with(view.context)
            .load(url)
            .into(view)
    }
}


