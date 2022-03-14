package com.francis.moviestest.service.api

import com.francis.moviestest.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IMoviesService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") api_key: String? = "a53ef3891e00404db7372320ce8abaab"): Response<MoviesResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key")api_key: String? = "a53ef3891e00404db7372320ce8abaab"): Response<MoviesResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key")api_key: String? = "a53ef3891e00404db7372320ce8abaab"): Response<MoviesResponse>
}