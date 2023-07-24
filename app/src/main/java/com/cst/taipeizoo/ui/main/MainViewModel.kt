package com.cst.taipeizoo.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.cst.taipeizoo.data.config.BUNDLE_DATA
import com.cst.taipeizoo.data.entities.MainEntity
import com.cst.taipeizoo.data.network.response.GetAnimalsResponse
import com.cst.taipeizoo.data.network.response.GetPlacesResponse
import com.cst.taipeizoo.utils.getParcelableData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    val entity = MainEntity()

    fun setPlaceTitle(
        bundle: Bundle
    ) {

        val data = bundle.getParcelableData(BUNDLE_DATA, GetPlacesResponse.Result.Place::class.java)

        if (data != null) {
            entity.title.value = data.name
        }
    }

    fun setAnimalTitle(
        bundle: Bundle
    ) {

        val data = bundle.getParcelableData(BUNDLE_DATA, GetAnimalsResponse.Result.Animal::class.java)

        if (data != null) {
            entity.title.value = data.nameCh
        }
    }
}
