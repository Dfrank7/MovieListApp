package com.francis.moviestest.data.repository

import androidx.lifecycle.LiveData
import com.francis.moviestest.home.model.MoviesResponse
import com.francis.moviestest.data.db.PopularMovieData
import com.francis.moviestest.data.db.TopRatedMovieData
import com.francis.moviestest.data.db.UpcomingMovieData
import com.francis.moviestest.home.model.domain.NetworkMoviesContainer
import com.francis.moviestest.data.local.IMoviesLocalDatasource
import com.francis.moviestest.data.remote.IMoviesRemoteDataSource
import com.francis.moviestest.utility.IAppDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MoviesRepository(
    private val moviesRemoteDataSource: IMoviesRemoteDataSource,
    private val moviesLocalDataSource: IMoviesLocalDatasource,
    private val iAppDispatchers: IAppDispatchers
):IMoviesRepository, CoroutineScope {
    override fun getRemotePopularMovies(
        successCallback: (MoviesResponse) -> Unit,
        errorCallback: (String) -> Unit
    ) {
        launch {
            moviesRemoteDataSource.getPopularMovies(
                { response ->
                    val popularMovieData = NetworkMoviesContainer(response.results)
                    savePopularList(popularMovieData)
                    successCallback.invoke(response)

                },

                {ex ->
                    errorCallback.invoke(ex.message ?: "Unknown error occured" )

                }
            )
        }
    }

    override fun getSavedPopularList(): LiveData<List<PopularMovieData>> {
        return moviesLocalDataSource.getPopularMovies()
    }

    override fun savePopularList(popularMovieData: NetworkMoviesContainer) {
        return moviesLocalDataSource.savePopularMovies(popularMovieData)
    }

    override fun getRemoteUpcomingMovies(
        successCallback: (MoviesResponse) -> Unit,
        errorCallback: (String) -> Unit
    ) {
        launch {
            moviesRemoteDataSource.getUpcomingMovies(
                { response ->
                    val upcomingMovieData = NetworkMoviesContainer(response.results)
                    saveUpcomingList(upcomingMovieData)
                    successCallback.invoke(response)
                },

                {ex ->
                    errorCallback.invoke(ex.message ?: "Unknown error occured" )

                }
            )
        }
    }

    override fun getSavedUpcomingList(): LiveData<List<UpcomingMovieData>> {
        return moviesLocalDataSource.getUpcomingMovies()
    }

    override fun saveUpcomingList(upcomingMovieData: NetworkMoviesContainer) {
        return moviesLocalDataSource.saveUpcomingMovies(upcomingMovieData)
    }

    override fun getRemoteTopMovies(
        successCallback: (MoviesResponse) -> Unit,
        errorCallback: (String) -> Unit
    ) {
        launch {
            moviesRemoteDataSource.getUpcomingMovies(
                { response ->
                    val topMovieData = NetworkMoviesContainer(response.results)
                    saveTopList(topMovieData)
                    successCallback.invoke(response)
                },

                {ex ->
                    errorCallback.invoke(ex.message ?: "Unknown error occured" )

                }
            )
        }
    }

    override fun getSavedTopList(): LiveData<List<TopRatedMovieData>> {
        return moviesLocalDataSource.getTopMovies()
    }

    override fun saveTopList(topRatedMovieData: NetworkMoviesContainer) {
        return moviesLocalDataSource.saveTopMovies(topRatedMovieData)
    }

    override fun clear() {
        moviesLocalDataSource.clear()
        moviesRemoteDataSource.clear()
        coroutineContext.cancel()
    }

    override val coroutineContext: CoroutineContext
        get() = iAppDispatchers.ui()
}