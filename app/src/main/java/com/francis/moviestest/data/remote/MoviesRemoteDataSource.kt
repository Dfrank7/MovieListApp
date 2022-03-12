package com.francis.moviestest.data.remote

import com.francis.moviestest.MoviesResponse
import com.francis.moviestest.service.api.IMoviesService
import com.francis.moviestest.utility.IAppDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class MoviesRemoteDataSource(
    private val moviesService: IMoviesService,
    private val iAppDispatchers: IAppDispatchers
): IMoviesRemoteDataSource, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = iAppDispatchers.io()

    override fun getPopularMovies(
        api_key: String,
        successCallback: (MoviesResponse) -> Unit,
        errorCallback: () -> Unit
    ) {
        launch {
            try {
                val response = moviesService.getPopularMovies(api_key)
                val body = response.body()

                if (response.isSuccessful && body!=null){
                    successCallback(body)
                }else{
                    errorCallback()
                }
            }catch (e: Exception){
                errorCallback()
            }
        }

    }

    override fun clear() {
        coroutineContext.cancel()
    }


}