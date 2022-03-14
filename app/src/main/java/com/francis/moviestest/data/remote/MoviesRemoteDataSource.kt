package com.francis.moviestest.data.remote

import com.francis.moviestest.model.MoviesResponse
import com.francis.moviestest.service.api.IMoviesService
import com.francis.moviestest.utility.IAppDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.Exception
import kotlin.coroutines.CoroutineContext

class MoviesRemoteDataSource(
    private val moviesService: IMoviesService,
    private val iAppDispatchers: IAppDispatchers
): IMoviesRemoteDataSource, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = iAppDispatchers.io()

    override fun getPopularMovies(
        successCallback: (MoviesResponse) -> Unit,
        errorCallback: (Exception) -> Unit
    ) {
        launch {
            try {
                val response = moviesService.getPopularMovies()
                val body = response.body()

                if (response.isSuccessful && body!=null){
                    successCallback(body)
                }else{
                    errorCallback.invoke(error("Movies is null"))
                }
            }catch (e: Exception){
                errorCallback.invoke(e)
            }
        }

    }

    override fun getUpcomingMovies(
        successCallback: (MoviesResponse) -> Unit,
        errorCallback: (Exception) -> Unit
    ) {
        launch {
            try {
                val response = moviesService.getUpcomingMovies()
                val body = response.body()

                if (response.isSuccessful && body!=null){
                    successCallback(body)
                }else{
                    errorCallback.invoke(error("Movies is null"))
                }
            }catch (e: Exception){
                errorCallback.invoke(e)
            }
        }
    }

    override fun clear() {
        coroutineContext.cancel()
    }


}