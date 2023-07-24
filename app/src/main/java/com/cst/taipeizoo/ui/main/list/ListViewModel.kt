package com.cst.taipeizoo.ui.main.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cst.taipeizoo.data.network.module.onError
import com.cst.taipeizoo.data.network.module.onException
import com.cst.taipeizoo.data.network.module.onSuccess
import com.cst.taipeizoo.data.network.response.GetPlacesResponse
import com.cst.taipeizoo.data.repositories.MainRepository
import com.cst.taipeizoo.utils.Coroutines.io
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    val placeList = MutableLiveData(listOf<GetPlacesResponse.Result.Place>())
    val throwMessage = MutableLiveData("")

    fun getPlaces() = io {

        mainRepository.getPlaces()
            .onSuccess {
                placeList.postValue(it?.result?.places)
            }
            .onError { _, message ->
                throwMessage.postValue(message)
            }
            .onException {
                throwMessage.postValue("ERROR")
            }
    }
}