package com.francis.moviestest.service.api

import com.francis.moviestest.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IMoviesService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key")api_key: String): Response<MoviesResponse>
}