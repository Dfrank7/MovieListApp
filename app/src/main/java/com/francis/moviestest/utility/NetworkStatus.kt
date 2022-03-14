package com.francis.moviestest.utility

import android.app.Application

class NetworkStatus(val app: Application) : INetworkStatus {
    override fun isConnected(): Boolean {
        return isConnectionAvailable(app)
    }
}