package dev.chu.toyapp.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dev.chu.toyapp.BuildConfig
import dev.chu.toyapp.GlobalApplication
import dev.chu.toyapp.common.Const
import dev.chu.toyapp.etc.extensions.TAG
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException

class Api {

    private val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()

    private var httpCacheDirectory: File =
        File(GlobalApplication.getInstance().cacheDir, "offlineCache")

    // region cache
    private val isConnected: Boolean
        private get() {
            try {
                val e = GlobalApplication.getInstance().getSystemService(
                    Context.CONNECTIVITY_SERVICE
                ) as ConnectivityManager
                val activeNetwork = e.activeNetworkInfo
                return activeNetwork != null && activeNetwork.isConnectedOrConnecting
            } catch (e: Exception) {
                Log.w(TAG, e.toString())
            }
            return false
        }

    private var OFFLINE_INTERCEPTOR: Interceptor = object : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            if (!isConnected) {
                val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
                request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .build()
            }
            return chain.proceed(request)
        }
    }

    private var ONLINE_INTERCEPTOR: Interceptor = object : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val response = chain.proceed(chain.request())
            val maxAge = 60 // read from cache
            return response.newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                .build()
        }
    }

    // 10 MB
    private val cache: Cache =
        Cache(httpCacheDirectory, (10 * 1024 * 1024).toLong())
//        Cache(GlobalApplication.getInstance().cacheDir, (10 * 1024 * 1024).toLong())
    // endregion

    private val okHttpClientBuilder =
        OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY
            })        // Retrofit 에서 통신 과정의 로그를 확인하기 위함. 로그의 level 을 지정
            cache(cache)
            addInterceptor(OFFLINE_INTERCEPTOR)
            addNetworkInterceptor(ONLINE_INTERCEPTOR)
        }

    fun <T> createService(serviceClass: Class<T>): T =
        Retrofit.Builder()
            .client(okHttpClientBuilder.build())
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(serviceClass)

    fun mockTestRetrofit() =
        Retrofit.Builder()
            .client(OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })        // Retrofit 에서 통신 과정의 로그를 확인하기 위함. 로그의 level 을 지정
            }.build())
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
}