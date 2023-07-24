package com.cst.taipeizoo.ui.main.list.place

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cst.taipeizoo.R
import com.cst.taipeizoo.data.config.BUNDLE_DATA
import com.cst.taipeizoo.data.network.response.GetAnimalsResponse
import com.cst.taipeizoo.data.network.response.GetPlacesResponse
import com.cst.taipeizoo.databinding.FragmentListBinding
import com.cst.taipeizoo.ui.base.BaseFragment
import com.cst.taipeizoo.utils.getParcelableData
import com.cst.taipeizoo.view.RecyclerDivider
import com.cst.taipeizoo.view.snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaceFragment : BaseFragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel by viewModels<PlaceViewModel>()

    private val itemClickListener = object : AnimalListAdapter.ItemClickListener {

        override fun onWebsiteClick(url: String) {
            openWeb(url)
        }

        override fun onItemClick(data: GetAnimalsResponse.Result.Animal) {

            val bundle = Bundle()
            bundle.putParcelable(BUNDLE_DATA, data)

            findNavController().navigate(R.id.action_place_fragment_to_animal_fragment, bundle)
        }
    }
    private val animalListAdapter = AnimalListAdapter(itemClickListener)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentListBinding.inflate(inflater, container, false).apply {

            with(recycler) {
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                setHasFixedSize(false)
                addItemDecoration(RecyclerDivider(requireContext(), RecyclerDivider.Color.GRAY))
                adapter = animalListAdapter
            }
        }

        val data = arguments?.getParcelableData(BUNDLE_DATA, GetPlacesResponse.Result.Place::class.java)

        if (data != null) {
            viewModel.setData(data)
        }

        with(viewModel) {
            animalList.observe(viewLifecycleOwner) { animalListAdapter.submitList(listOf(viewModel.header) + it) }
            throwMessage.observe(viewLifecycleOwner) { showThrowMessage(it) }
        }

        return binding.root
    }

    private fun openWeb(
        url: String
    ) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    private fun showThrowMessage(
        message: String
    ) {

        if (message.isBlank()) return

        binding.root.snackbar(message)

        viewModel.throwMessage.value = ""
    }
}