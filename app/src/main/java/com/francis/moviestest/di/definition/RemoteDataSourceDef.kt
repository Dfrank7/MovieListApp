package com.francis.moviestest.di.definition

import com.francis.moviestest.data.remote.IMoviesRemoteDataSource
import com.francis.moviestest.data.remote.MoviesRemoteDataSource
import com.francis.moviestest.service.api.IMoviesService
import com.francis.moviestest.utility.IAppDispatchers

fun createMoviesRemoteDataSource(
    mIMoviesService: IMoviesService,
    iAppDispatchers: IAppDispatchers
): IMoviesRemoteDataSource {
    return MoviesRemoteDataSource(mIMoviesService, iAppDispatchers)
}