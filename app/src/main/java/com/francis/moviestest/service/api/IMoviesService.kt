package com.francis.moviestest.service.api

import com.francis.moviestest.home.model.MoviesResponse
import com.francis.moviestest.utility.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IMoviesService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") api_key: String? = Constants.apiKey): Response<MoviesResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key")api_key: String? = Constants.apiKey): Response<MoviesResponse>

    //Replace Latest with Top Rated as Latest response isn't returning the list of movies.
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key")api_key: String? = Constants.apiKey): Response<MoviesResponse>
}