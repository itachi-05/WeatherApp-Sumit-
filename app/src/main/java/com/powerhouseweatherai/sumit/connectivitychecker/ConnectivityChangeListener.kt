package com.powerhouseweatherai.sumit.connectivitychecker

interface ConnectivityChangeListener {
    fun onNetworkConnected()
    fun onNetworkDisconnected()
}
