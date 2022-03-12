package com.francis.moviestest.di

import com.francis.moviestest.di.definition.createAuthOkHttpClient
import com.francis.moviestest.di.definition.createMoviesService
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.dsl.single

private const val NETWORK = "network"
val networkModule = module {

    single(named(NETWORK)){ createAuthOkHttpClient(androidApplication())}

    single { createMoviesService(get(named(NETWORK))) }

}

val repoModule = module {

}