package com.francis.moviestest

import android.app.Application
import androidx.multidex.BuildConfig
import timber.log.Timber

class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        plantTimber()
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