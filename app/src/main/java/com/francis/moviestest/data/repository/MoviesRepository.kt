package com.francis.moviestest.data.repository

import androidx.lifecycle.LiveData
import com.francis.moviestest.MoviesResponse
import com.francis.moviestest.data.db.PopularMovieData
import com.francis.moviestest.data.domain.NetworkMoviesContainer
import com.francis.moviestest.data.domain.toPopulatEntity
import com.francis.moviestest.data.local.MoviesLocalDataSource
import com.francis.moviestest.data.remote.MoviesRemoteDataSource
import com.francis.moviestest.utility.IAppDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MoviesRepository(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource,
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

    override val coroutineContext: CoroutineContext
        get() = TODO("Not yet implemented")
}