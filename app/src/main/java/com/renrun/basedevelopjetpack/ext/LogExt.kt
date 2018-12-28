package com.renrun.basedevelopjetpack.ext

import android.app.Activity
import androidx.fragment.app.Fragment
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

/**
 * Created by vence on 2018/12/21 16:42
 * 邮箱 ：vence0815@foxmail.com
 */
/**
 * 拓展日志
 */

/**
 * only message
 */
val formatSimpleStrategy = PrettyFormatStrategy.newBuilder()
    .showThreadInfo(false)
    .methodCount(0)
    .build()

fun formateSimpleLog() {
    Logger.clearLogAdapters()
    Logger.addLogAdapter(AndroidLogAdapter(formatSimpleStrategy))
}

fun formateNormal() {
    Logger.clearLogAdapters()
    Logger.addLogAdapter(AndroidLogAdapter())
}

fun Fragment.logSimpleE(msg: String) {
    formateSimpleLog()
    Logger.e(msg)
}

fun Fragment.logSimpleD(msg: String) {
    formateSimpleLog()
    Logger.d(msg)
}

fun Fragment.logSimpleI(msg: String) {
    formateSimpleLog()
    Logger.i(msg)
}

fun Fragment.logSimpleW(msg: String) {
    formateSimpleLog()
    Logger.w(msg)
}

fun Fragment.logSimpleV(msg: String) {
    formateSimpleLog()
    Logger.v(msg)
}


// ---------------

fun Fragment.logE(msg: String) {
    formateNormal()
    Logger.e(msg)
}

fun Fragment.logD(msg: String) {
    formateNormal()
    Logger.d(msg)
}

fun Fragment.logI(msg: String) {
    formateNormal()
    Logger.i(msg)
}

fun Fragment.logW(msg: String) {
    formateNormal()
    Logger.w(msg)
}

fun Fragment.logV(msg: String) {
    formateNormal()
    Logger.v(msg)
}
//   ---------------------------------------------------------------------------
fun Activity.logE(msg: String) {
    formateNormal()
    Logger.e(msg)
}

fun Activity.logD(msg: String) {
    formateNormal()
    Logger.d(msg)
}

fun Activity.logI(msg: String) {
    formateNormal()
    Logger.i(msg)
}

fun Activity.logW(msg: String) {
    formateNormal()
    Logger.w(msg)
}

fun Activity.logV(msg: String) {
    formateNormal()
    Logger.v(msg)
}