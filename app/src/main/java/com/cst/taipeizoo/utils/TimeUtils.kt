package com.cst.taipeizoo.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

val DATE_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/M/dd")
val CHINESE_DATE_FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年M月dd日")

fun String.toChineseDate(): String = LocalDate.parse(this, DATE_FORMAT).format(CHINESE_DATE_FORMAT)