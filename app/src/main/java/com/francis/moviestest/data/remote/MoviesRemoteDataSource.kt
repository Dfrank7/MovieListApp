package com.francis.moviestest.data.remote

import com.francis.moviestest.service.api.IMoviesService
import com.francis.moviestest.utility.IAppDispatchers

class MoviesRemoteDataSource(private val moviesService: IMoviesService,
private val iAppDispatchers: IAppDispatchers) {
}