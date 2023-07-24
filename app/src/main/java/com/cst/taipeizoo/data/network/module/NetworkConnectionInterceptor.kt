package com.cst.taipeizoo.data.network.module

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.cst.taipeizoo.utils.NoInternetException
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

//Check network connection
class NetworkConnectionInterceptor @Inject constructor(
    @ApplicationContext context: Context
) : Interceptor {

    private val appContext = context.applicationContext

    /**
     * @throws NoInternetException If there is no Internet.
     */
    override fun intercept(chain: Interceptor.Chain): Response = when {

        !isInternetAvailable() -> throw NoInternetException("No Internet")

        else -> chain.proceed(chain.request())
    }

    private fun isInternetAvailable(): Boolean {

        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        connectivityManager?.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {

            return when {
                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        }

        return false
    }
}
