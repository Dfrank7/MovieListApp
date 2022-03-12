package com.francis.moviestest.data.remote

import com.francis.moviestest.MoviesResponse
import com.francis.moviestest.data.IDataSource

interface IMoviesRemoteDataSource : IDataSource {

    fun getPopularMovies(
        api_key: String,
        successCallback: (MoviesResponse) -> Unit,
        errorCallback: () -> Unit
    )
}