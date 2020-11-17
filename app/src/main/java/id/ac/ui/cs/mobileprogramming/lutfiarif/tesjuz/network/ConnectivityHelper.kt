package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.network

import android.content.Context
import android.net.ConnectivityManager

class ConnectivityHelper {

    fun isConnectedToNetwork(context: Context): Boolean {
        val cm: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var isConnected = false
        if (cm != null) {
            val activeNetwork = cm.activeNetworkInfo
            isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
        return isConnected
    }
}