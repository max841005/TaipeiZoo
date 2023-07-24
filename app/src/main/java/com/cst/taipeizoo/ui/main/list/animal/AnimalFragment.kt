package com.cst.taipeizoo.ui.main.list.animal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.cst.taipeizoo.R
import com.cst.taipeizoo.data.config.BUNDLE_DATA
import com.cst.taipeizoo.data.network.response.GetAnimalsResponse
import com.cst.taipeizoo.databinding.FragmentAnimalContentBinding
import com.cst.taipeizoo.ui.base.BaseFragment
import com.cst.taipeizoo.utils.getParcelableData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimalFragment : BaseFragment() {

    private lateinit var binding: FragmentAnimalContentBinding
    private val viewModel by viewModels<AnimalViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAnimalContentBinding.inflate(inflater, container, false).apply {

            lifecycleOwner = viewLifecycleOwner
            entity = viewModel.entity
        }

        val data = arguments?.getParcelableData(BUNDLE_DATA, GetAnimalsResponse.Result.Animal::class.java)

        if (data != null) {
            setData(data)
        }

        return binding.root
    }

    private fun setData(
        data: GetAnimalsResponse.Result.Animal
    ) {

        viewModel.setData(data)

        Glide.with(this)
            .load(data.picUrl)
            .centerCrop()
            .placeholder(R.drawable.icon_image)
            .into(binding.image)
    }
}