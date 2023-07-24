package com.cst.taipeizoo.ui.main.list.animal

import androidx.lifecycle.ViewModel
import com.cst.taipeizoo.data.entities.AnimalContentEntity
import com.cst.taipeizoo.data.network.response.GetAnimalsResponse
import com.cst.taipeizoo.utils.toChineseDate
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject constructor() : ViewModel() {

    var entity = AnimalContentEntity()

    fun setData(
        data: GetAnimalsResponse.Result.Animal
    ) {

        with(entity) {
            nameCh.value = data.nameCh
            nameLa.value = data.nameLatin
            category.value = "${data.phylum} ${data.clazz} ${data.order} ${data.family}"
            distribution.value = data.distribution
            feature.value = data.feature
            behavior.value = data.behavior
        }

        kotlin.runCatching {
            data.update.toChineseDate()
        }.onSuccess {
            entity.latestDate.value = it
        }.onFailure {
            entity.latestDate.value = "N/A"
        }
    }
}