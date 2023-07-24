package com.cst.taipeizoo.utils

import org.junit.Assert
import org.junit.Test

class TimeUtilsKtTest {

    @Test
    fun toChineseDate() {

        val date = "2023/7/24"
        val chineseDate = date.toChineseDate()

        Assert.assertEquals("2023年7月24日", chineseDate)
    }
}