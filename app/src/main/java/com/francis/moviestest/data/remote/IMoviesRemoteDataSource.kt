package com.francis.moviestest.data.remote

import com.francis.moviestest.home.model.MoviesResponse
import com.francis.moviestest.data.IDataSource

interface IMoviesRemoteDataSource : IDataSource {

    fun getPopularMovies(
        successCallback: (MoviesResponse) -> Unit,
        errorCallback: (Exception) -> Unit
    )

    fun getUpcomingMovies(
        successCallback: (MoviesResponse) -> Unit,
        errorCallback: (Exception) -> Unit
    )

    fun getTopMovies(
        successCallback: (MoviesResponse) -> Unit,
        errorCallback: (Exception) -> Unit
    )
}