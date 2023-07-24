package com.cst.taipeizoo.ui.main.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cst.taipeizoo.R
import com.cst.taipeizoo.data.entities.item.PlaceItemEntity
import com.cst.taipeizoo.data.network.response.GetPlacesResponse
import com.cst.taipeizoo.databinding.ItemPlaceBinding

class PlaceListAdapter(
    private val itemClickListener: ItemClickListener
) : ListAdapter<GetPlacesResponse.Result.Place, PlaceListAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = getItem(position)

        with(holder.binding) {
            click = View.OnClickListener { itemClickListener.onClick(data) }
            entity = data.toPlaceItemEntity(holder.itemView.context.getString(R.string.no_place_info))
        }

        Glide.with(holder.binding.root)
            .load(data.picUrl)
            .centerCrop()
            .placeholder(R.drawable.icon_image)
            .into(holder.binding.image)
    }

    private fun GetPlacesResponse.Result.Place.toPlaceItemEntity(
        defaultMemo: String
    ) = PlaceItemEntity(
        title = this.name,
        content = this.info,
        memo = when (this.memo) {
            "" -> defaultMemo
            else -> this.memo
        }
    )

    companion object {

        val diffCallback = object : DiffUtil.ItemCallback<GetPlacesResponse.Result.Place>() {

            override fun areItemsTheSame(
                oldItem: GetPlacesResponse.Result.Place,
                newItem: GetPlacesResponse.Result.Place
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: GetPlacesResponse.Result.Place,
                newItem: GetPlacesResponse.Result.Place
            ) = oldItem.name == newItem.name
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ItemPlaceBinding.bind(itemView)
    }

    fun interface ItemClickListener {
        fun onClick(data: GetPlacesResponse.Result.Place)
    }
}