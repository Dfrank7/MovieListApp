package com.francis.moviestest.di.definition

import com.francis.moviestest.data.db.MovieHelper
import com.francis.moviestest.data.local.IMoviesLocalDatasource
import com.francis.moviestest.data.remote.IMoviesRemoteDataSource
import com.francis.moviestest.data.repository.IMoviesRepository
import com.francis.moviestest.data.repository.MoviesRepository
import com.francis.moviestest.utility.IAppDispatchers


fun createMoviesRepository(
    moviesLocalDataSource: IMoviesLocalDatasource,
    moviesRemoteDataSource: IMoviesRemoteDataSource,
    iAppDispatchers: IAppDispatchers
): IMoviesRepository{
    return MoviesRepository(moviesRemoteDataSource, moviesLocalDataSource,iAppDispatchers)
}