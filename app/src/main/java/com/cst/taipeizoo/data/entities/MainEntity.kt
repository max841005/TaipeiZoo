package com.cst.taipeizoo.data.entities

import androidx.lifecycle.MutableLiveData

data class MainEntity(
    val title: MutableLiveData<CharSequence> = MutableLiveData("")
)