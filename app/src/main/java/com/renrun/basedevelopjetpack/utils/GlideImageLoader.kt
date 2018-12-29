package com.renrun.basedevelopjetpack.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.youth.banner.loader.ImageLoader

/**
 * Created by vence on 2018/12/28 17:46
 * 邮箱 ：vence0815@foxmail.com
 */
class GlideImageLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any?, imageView: ImageView) {
        Glide.with(context.getApplicationContext())
            .load(path)
            .into(imageView)
    }

}