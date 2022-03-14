package com.francis.moviestest.di

import android.app.Application
import com.francis.moviestest.MoviesApp
import com.francis.moviestest.data.db.MovieHelper
import com.francis.moviestest.data.db.MoviesDb
import com.francis.moviestest.utility.*

fun createMovieHelper(app: Application): MovieHelper{
    val database = MoviesDb.getInstance(app)
    return MovieHelper(database)
}

fun createCoroutineDispatcher(): IAppDispatchers = AppDispatchers()

fun createNetworkStatus(app: Application): INetworkStatus = NetworkStatus(app)

