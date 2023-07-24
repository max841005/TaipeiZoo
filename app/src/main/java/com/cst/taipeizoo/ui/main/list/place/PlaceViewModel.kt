package com.cst.taipeizoo.ui.main.list.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cst.taipeizoo.data.entities.item.PlaceContentItemEntity
import com.cst.taipeizoo.data.network.module.onError
import com.cst.taipeizoo.data.network.module.onException
import com.cst.taipeizoo.data.network.module.onSuccess
import com.cst.taipeizoo.data.network.response.GetPlacesResponse
import com.cst.taipeizoo.data.repositories.MainRepository
import com.cst.taipeizoo.utils.Coroutines
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlaceViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    var header = PlaceContentItemEntity.Header()
    val animalList = MutableLiveData(listOf<PlaceContentItemEntity>())
    val throwMessage = MutableLiveData("")

    fun setData(
        data: GetPlacesResponse.Result.Place
    ) {

        header = PlaceContentItemEntity.Header(
            picUrl = data.picUrl,
            content = data.info,
            memo = data.memo,
            category = data.category,
            webUrl = data.url
        )

        getAnimals(data.name)
    }

    private fun getAnimals(
        place: String
    ) = Coroutines.io {

        mainRepository.getAnimals()
            .onSuccess {

                val resultList = it?.result?.animals?.filter { animal ->
                    animal.location == place
                }?.map {data ->
                    PlaceContentItemEntity.Animal(data)
                }

                animalList.postValue(resultList)
            }
            .onError { _, message ->
                throwMessage.postValue(message)
            }
            .onException {
                throwMessage.postValue("ERROR")
            }
    }
}