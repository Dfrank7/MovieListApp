package com.francis.moviestest.di.definition

import com.francis.moviestest.data.db.MovieHelper
import com.francis.moviestest.data.local.IMoviesLocalDatasource
import com.francis.moviestest.data.local.MoviesLocalDataSource
import com.francis.moviestest.utility.IAppDispatchers

fun createLocalDataSource(
    movieHelper: MovieHelper,
    iAppDispatchers: IAppDispatchers
): IMoviesLocalDatasource {
    return MoviesLocalDataSource(movieHelper, iAppDispatchers)
}