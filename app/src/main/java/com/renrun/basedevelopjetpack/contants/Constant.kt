package com.renrun.basedevelopjetpack.contants

import com.renrun.basedevelopjetpack.app.MyApplication
import java.io.File

/**
 * Created by vence on 2018/12/25 16:16
 * 邮箱 ：vence0815@foxmail.com
 *一些常用的字符串
 */
const val NAMESPACE = "http://www.wanandroid.com/"


val PATH_DATA = MyApplication.getInstance().getCacheDir().getAbsolutePath() + File.separator + "reFreshData"

val PATH_CACHE = PATH_DATA + "/NetCache"

//图片前缀
var imgaUrlPre = NAMESPACE + "file/image/"


const val CONTENT_CID_KEY = "cid"