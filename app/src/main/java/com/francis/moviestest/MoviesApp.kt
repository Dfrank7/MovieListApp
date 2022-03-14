package com.francis.moviestest

import android.app.Application
import androidx.multidex.BuildConfig
import com.francis.moviestest.data.db.MoviesDb
import com.francis.moviestest.di.networkModule
import com.francis.moviestest.di.repoModule
import com.francis.moviestest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MoviesApp : Application() {

    val moviesDb by lazy {
        MoviesDb.getInstance(this).getMoviesDb()
    }

    override fun onCreate() {
        super.onCreate()
        plantTimber()
        initKoin()
    }

    private fun initKoin(){
        startKoin {
            androidContext(this@MoviesApp)
            modules(listOf(networkModule, viewModelModule, repoModule))
        }
    }

    private fun plantTimber(){
        if (BuildConfig.DEBUG){
            Timber.plant(object : Timber.DebugTree(){
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return String.format(
                        "%s @ (Line No. %$) :::",
                        super.createStackElementTag(element),
                        element.lineNumber
                    )
                }

            })
        }
    }
}