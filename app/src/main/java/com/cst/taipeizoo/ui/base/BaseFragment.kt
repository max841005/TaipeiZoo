package com.cst.taipeizoo.ui.base

import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), View.OnClickListener {

    override fun onClick(view: View) {}
}