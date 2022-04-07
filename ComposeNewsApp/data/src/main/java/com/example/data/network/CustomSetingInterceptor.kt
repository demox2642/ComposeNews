package com.example.data.network

import okhttp3.Interceptor
import okhttp3.Response

class CustomSetingInterceptor : Interceptor {

//    override fun intercept(chain: Interceptor.Chain): Response {
//        val originalRequest = chain.request()

    //        val modifiRequest = originalRequest.newBuilder()
//            .header("apikey", Constants.API_KEY)
//            .addQueryParameter(, Constants.API_KEY)
//            .build()
//        val url = chain.request()
//            .url()
//            .newBuilder()
//            .addQueryParameter(, Constants.API_KEY)
//            .build()
//
//        val request = chain.request()
//            .newBuilder()
//            .url(url)
//            .build()
//        return chain.proceed(modifiRequest)
//    }
    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("Not yet implemented")
    }
}
