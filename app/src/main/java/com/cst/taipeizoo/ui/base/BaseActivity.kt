package com.cst.taipeizoo.ui.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(v: View?) {}
}