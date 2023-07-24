package com.cst.taipeizoo.data.repositories

import com.cst.taipeizoo.data.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val api: ApiService
) {

    suspend fun getPlaces() = api.getPlaces()

    suspend fun getAnimals() = api.getAnimals()
}