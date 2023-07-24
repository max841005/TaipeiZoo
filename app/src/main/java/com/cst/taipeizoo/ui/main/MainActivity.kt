package com.cst.taipeizoo.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.cst.taipeizoo.R
import com.cst.taipeizoo.databinding.ActivityMainBinding
import com.cst.taipeizoo.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }
    private val viewModel by viewModels<MainViewModel>()

    private val fragment: NavHostFragment by lazy { supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {

            lifecycleOwner = this@MainActivity
            click = this@MainActivity
            entity = viewModel.entity

            toolbar.setNavigationOnClickListener { this@MainActivity.fragment.navController.popBackStack() }
        }

        fragment.navController.addOnDestinationChangedListener { _, destination, bundle ->
            destinationChange(destination, bundle)
        }
    }

    private fun destinationChange(
        destination: NavDestination,
        bundle: Bundle?
    ) {

        when (destination.id) {

            R.id.list_fragment -> {

                viewModel.entity.title.value = getString(R.string.app_name)

                binding.toolbar.navigationIcon = null
            }

            R.id.place_fragment -> {

                bundle?.let { viewModel.setPlaceTitle(it) }

                binding.toolbar.setNavigationIcon(R.drawable.icon_arrow_back)
            }

            R.id.animal_fragment -> {

                bundle?.let { viewModel.setAnimalTitle(it) }

                binding.toolbar.setNavigationIcon(R.drawable.icon_arrow_back)
            }
        }
    }
}