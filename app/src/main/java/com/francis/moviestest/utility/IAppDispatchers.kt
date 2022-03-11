package com.francis.moviestest.utility

import kotlinx.coroutines.CoroutineDispatcher

interface IAppDispatchers {

    fun ui(): CoroutineDispatcher

    fun io(): CoroutineDispatcher

    fun default(): CoroutineDispatcher
}