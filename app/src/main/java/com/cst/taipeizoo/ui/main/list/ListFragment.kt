package com.cst.taipeizoo.ui.main.list

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
import com.cst.taipeizoo.databinding.FragmentListBinding
import com.cst.taipeizoo.ui.base.BaseFragment
import com.cst.taipeizoo.view.RecyclerDivider
import com.cst.taipeizoo.view.snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : BaseFragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel by viewModels<ListViewModel>()

    private val itemClickListener = PlaceListAdapter.ItemClickListener {

        val bundle = Bundle()
        bundle.putParcelable(BUNDLE_DATA, it)
        findNavController().navigate(R.id.action_list_fragment_to_place_fragment, bundle)
    }
    private val placeListAdapter = PlaceListAdapter(itemClickListener)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentListBinding.inflate(inflater, container, false).apply {

            with(recycler) {
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                setHasFixedSize(true)
                addItemDecoration(RecyclerDivider(requireContext(), RecyclerDivider.Color.GRAY))
                adapter = placeListAdapter
            }
        }

        with(viewModel) {
            placeList.observe(viewLifecycleOwner) { placeListAdapter.submitList(it) }
            throwMessage.observe(viewLifecycleOwner) { showThrowMessage(it) }
        }

        viewModel.getPlaces()

        return binding.root
    }

    private fun showThrowMessage(
        message: String
    ) {

        if (message.isBlank()) return

        binding.root.snackbar(message)

        viewModel.throwMessage.value = ""
    }
}