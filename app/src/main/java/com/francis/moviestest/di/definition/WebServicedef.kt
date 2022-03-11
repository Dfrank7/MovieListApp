package com.francis.moviestest.di.definition

import com.francis.moviestest.service.api.IMoviesService
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val base_url = "http://api.themoviedb.org/3/"
fun createMoviesService(okHttpClient: OkHttpClient): IMoviesService{
    val retrofit = buildRetrofit(okHttpClient, base_url)
    return retrofit.create(IMoviesService::class.java)
}


private fun buildRetrofit(client: OkHttpClient, baseUrl: String) : Retrofit{
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .client(client)
        .build()
}