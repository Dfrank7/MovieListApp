package com.francis.moviestest.data.local

import androidx.lifecycle.LiveData
import com.francis.moviestest.MoviesResponse
import com.francis.moviestest.data.db.MovieHelper
import com.francis.moviestest.data.db.PopularMovieData
import com.francis.moviestest.data.domain.NetworkMoviesContainer
import com.francis.moviestest.utility.IAppDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MoviesLocalDataSource(
    private val movieHelper: MovieHelper,
    private val iAppDispatchers: IAppDispatchers
): IMoviesLocalDatasource, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = iAppDispatchers.ui()

    override fun getPopularMovies(): LiveData<List<PopularMovieData>> {
        return movieHelper.getPopularList()
    }

    override fun savePopularMovies(movies: NetworkMoviesContainer) {
        launch {
            movieHelper.savePopularList(movies)
        }
    }

    override fun clear() {
        coroutineContext.cancel()
    }


}