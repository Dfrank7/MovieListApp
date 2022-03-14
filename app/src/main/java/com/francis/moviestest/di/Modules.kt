package com.francis.moviestest.di

import com.francis.moviestest.di.definition.*
import com.francis.moviestest.home.viewmodel.MoviesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val NETWORK = "network"
val networkModule = module {

    single(named(NETWORK)){ createAuthOkHttpClient(androidApplication())}

    single { createMoviesService(get(named(NETWORK))) }

}

val repoModule = module {

    //Coroutine dispatchers
    single { createCoroutineDispatcher() }

    //Helper
    single { createMovieHelper(androidApplication()) }
    single { createNetworkStatus(androidApplication()) }

    //Movies repository
    single { createLocalDataSource(get(), get()) }
    single { createMoviesRemoteDataSource(get(), get(), get()) }
    single { createMoviesRepository(get(), get(), get()) }

}

val viewModelModule = module {
    viewModel{MoviesViewModel(get(), get())}
}