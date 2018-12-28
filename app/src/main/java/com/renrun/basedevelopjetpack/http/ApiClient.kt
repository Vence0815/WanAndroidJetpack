package com.renrun.basedevelopjetpack.http

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.renrun.basedevelopjetpack.BuildConfig
import com.renrun.basedevelopjetpack.contants.NAMESPACE
import com.renrun.basedevelopjetpack.contants.PATH_CACHE
import com.renrun.basedevelopjetpack.http.interceptor.BasicParamsInterceptor
import com.renrun.basedevelopjetpack.http.interceptor.VHttpLoggingInterceptor
import com.renrun.basedevelopjetpack.utils.CommonUtils
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by vence on 2018/12/25 15:51
 * 邮箱 ：vence0815@foxmail.com
 */
class ApiClient {

    lateinit var api: Api

    private object Holder {
        val INSTANCE = ApiClient()
    }

    companion object {
        val instance by lazy { Holder.INSTANCE }
    }

    fun init() {  //在Application的onCreate中调用一次即可
        val builder = OkHttpClient().newBuilder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(VHttpLoggingInterceptor(VHttpLoggingInterceptor.Level.RR))
        }
        val cacheFile = File(PATH_CACHE)
        val cache = Cache(cacheFile, (1024 * 1024 * 50).toLong())
        val cacheInterceptor = { chain: Interceptor.Chain ->
            var request = chain.request()
            if (!CommonUtils.isNetworkConnected()) {
                request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build()
            }
            val response = chain.proceed(request)
            if (CommonUtils.isNetworkConnected()) {
                val maxAge = 0
                // 有网络时, 不缓存, 最大保存时长为0
                response.newBuilder()
                    .header("Cache-Control", "public, max-age=$maxAge")
                    .removeHeader("Pragma")
                    .build()
            } else {
                // 无网络时，设置超时为4周
                val maxStale = 60 * 60 * 24 * 28
                response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .removeHeader("Pragma")
                    .build()
            }
            response
        }

        val basicParamsInterceptor = BasicParamsInterceptor.Builder()
            .addQueryParam("os", "Android")
            .addHeaderParam("Content-Type", "application/json")
            .build()

        //设置基础的内容每个接口默认带上的
        builder.addInterceptor(basicParamsInterceptor)
        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor)
        builder.addInterceptor(cacheInterceptor)
        builder.cache(cache)
        //添加Stetho
        builder.addNetworkInterceptor(StethoInterceptor())
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS)
        builder.readTimeout(20, TimeUnit.SECONDS)
        builder.writeTimeout(20, TimeUnit.SECONDS)
        //错误重连
        builder.retryOnConnectionFailure(true)

        val retrofit = Retrofit.Builder()
            .baseUrl(NAMESPACE)
//            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(builder.build())
            .build()
        api = retrofit.create(Api::class.java)
    }

}