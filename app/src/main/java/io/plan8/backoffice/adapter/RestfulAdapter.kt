package io.plan8.backoffice.adapter

import android.annotation.SuppressLint
import android.content.Context

import com.google.gson.GsonBuilder
import io.plan8.backoffice.ApplicationManager

import io.plan8.backoffice.api.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by SSozi on 2017. 11. 7..
 */

class RestfulAdapter {
    private lateinit var context: Context
    private lateinit var token: String

    companion object {
        @SuppressLint("StaticFieldLeak")
        var instance: RestfulAdapter? = null
        var retrofitServiceApi: ApiService? = null

        @Synchronized
        fun build(context: Context): RestfulAdapter {
            if (instance == null) instance = RestfulAdapter(context)
            return instance as RestfulAdapter
        }
    }

    constructor()

    constructor(context: Context) {
        this.context = context
//        this.token = token
    }

    //Retrofit 설정
    //인터페이스 연결
    val serviceApi: ApiService?
        get() {
            if (retrofitServiceApi == null) {
                val client = OkHttpClient.Builder().build()

                val gson = GsonBuilder()
                        .setLenient()
                        .create()
                retrofitServiceApi = Retrofit.Builder()
                        .baseUrl(ApplicationManager.BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build().create(ApiService::class.java)
            }
            return retrofitServiceApi
        }

    val authServiceApi: ApiService?
        get() {
            if (retrofitServiceApi == null) {
                val client: OkHttpClient.Builder? = OkHttpClient().newBuilder().addInterceptor(object : Interceptor{
                    override fun intercept(chain: Interceptor.Chain?): Response {
                        val request: Request = chain!!.request().newBuilder().addHeader("authorization", token).build()
                        return chain.proceed(request)
                    }

                })


                val gson = GsonBuilder()
                        .setLenient()
                        .create()
//                retrofitServiceApi = Retrofit.Builder()
//                        .baseUrl(ApplicationManager.BASE_URL)
//                        .client(client!!)
//                        .addConverterFactory(GsonConverterFactory.create(gson))
//                        .build().create(ApiService::class.java)
            }
            return retrofitServiceApi
        }
}
