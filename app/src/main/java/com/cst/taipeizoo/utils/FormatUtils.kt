package com.cst.taipeizoo.utils

import android.os.Build
import android.os.Bundle

fun <T> Bundle.getParcelableData(key: String, clazz: Class<T>) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    this.getParcelable(key, clazz)
}
else {
    @Suppress("DEPRECATION")
    this.getParcelable(key) as T?
}