package com.cst.taipeizoo.data.network.module

import com.cst.taipeizoo.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private const val BASE_URL = "https://data.taipei/api/v1/dataset/"

    @Singleton
    @Provides
    fun providesOkHttpClient(
        networkConnectionInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(networkConnectionInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideApiModule(
        okHttpClient: OkHttpClient
    ): ApiService = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(ApiCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}