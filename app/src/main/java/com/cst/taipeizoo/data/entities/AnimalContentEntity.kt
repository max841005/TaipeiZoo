package com.cst.taipeizoo.data.entities

import androidx.lifecycle.MutableLiveData

data class AnimalContentEntity(
    val nameCh: MutableLiveData<CharSequence> = MutableLiveData(""),
    val nameLa: MutableLiveData<CharSequence> = MutableLiveData(""),
    val category: MutableLiveData<CharSequence> = MutableLiveData(""),
    val distribution: MutableLiveData<CharSequence> = MutableLiveData(""),
    val feature: MutableLiveData<CharSequence> = MutableLiveData(""),
    val behavior: MutableLiveData<CharSequence> = MutableLiveData(""),
    val latestDate: MutableLiveData<CharSequence> = MutableLiveData("")
)