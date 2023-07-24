package com.cst.taipeizoo.data.network

import com.cst.taipeizoo.data.network.module.ApiResult
import com.cst.taipeizoo.data.network.response.GetAnimalsResponse
import com.cst.taipeizoo.data.network.response.GetPlacesResponse
import retrofit2.http.GET

interface ApiService {

    @GET("5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    suspend fun getPlaces(): ApiResult<GetPlacesResponse>

    @GET("a3e2b221-75e0-45c1-8f97-75acbd43d613?scope=resourceAquire")
    suspend fun getAnimals(): ApiResult<GetAnimalsResponse>
}