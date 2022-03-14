package com.francis.moviestest.di.definition

import android.app.Application
import androidx.multidex.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit

fun createAuthOkHttpClient(app: Application): OkHttpClient{
    val builder = OkHttpClient.Builder()
        .addInterceptor(buildLoggingInterceptor())
        .readTimeout(80, TimeUnit.SECONDS)
        .writeTimeout(2, TimeUnit.MINUTES)
        .connectTimeout(80, TimeUnit.SECONDS)

    if (BuildConfig.DEBUG){
        builder.addNetworkInterceptor(StethoInterceptor())
    }
    return builder.build()
}

private fun buildLoggingInterceptor(): Interceptor {
    return HttpLoggingInterceptor {messsage -> Timber.d("Okhttp: %s", messsage)}.apply {
        level = HttpLoggingInterceptor.Level.BODY

    }
}