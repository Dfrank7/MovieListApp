package com.francis.moviestest.data.remote

import com.francis.moviestest.MoviesResponse
import com.francis.moviestest.data.IDataSource

interface IMoviesRemoteDataSource : IDataSource {

    fun getPopularMovies(
        successCallback: (MoviesResponse) -> Unit,
        errorCallback: (Exception) -> Unit
    )
}