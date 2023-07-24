package com.cst.taipeizoo.ui.main.list.place

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cst.taipeizoo.R
import com.cst.taipeizoo.data.entities.item.PlaceContentItemEntity
import com.cst.taipeizoo.data.network.response.GetAnimalsResponse
import com.cst.taipeizoo.databinding.ItemPlaceContentAnimalBinding
import com.cst.taipeizoo.databinding.ItemPlaceContentHeaderBinding

class AnimalListAdapter(
    private val itemClickListener: ItemClickListener
) : ListAdapter<PlaceContentItemEntity, RecyclerView.ViewHolder>(diffCallback) {

    enum class ItemViewType {
        HEADER, ANIMAL
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is PlaceContentItemEntity.Header -> ItemViewType.HEADER.ordinal
        is PlaceContentItemEntity.Animal -> ItemViewType.ANIMAL.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            ItemViewType.HEADER.ordinal -> HeaderViewHolder.from(parent)
            else -> AnimalViewHolder.from(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {

            is HeaderViewHolder -> {

                val data = getItem(position) as PlaceContentItemEntity.Header

                with(holder.binding) {
                    click = View.OnClickListener { itemClickListener.onWebsiteClick(data.webUrl) }
                    entity = data
                }

                Glide.with(holder.binding.root)
                    .load(data.picUrl)
                    .centerCrop()
                    .placeholder(R.drawable.icon_image)
                    .into(holder.binding.image)
            }

            is AnimalViewHolder -> {

                val data = getItem(position) as PlaceContentItemEntity.Animal

                with(holder.binding) {
                    click = View.OnClickListener { data.data.let { animal -> itemClickListener.onItemClick(animal) } }
                    entity = data
                }

                Glide.with(holder.binding.root)
                    .load(data.picUrl)
                    .centerCrop()
                    .placeholder(R.drawable.icon_image)
                    .into(holder.binding.image)
            }
        }
    }

    companion object {

        val diffCallback = object : DiffUtil.ItemCallback<PlaceContentItemEntity>() {

            override fun areItemsTheSame(
                oldItem: PlaceContentItemEntity,
                newItem: PlaceContentItemEntity
            ) = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: PlaceContentItemEntity,
                newItem: PlaceContentItemEntity
            ) = oldItem == newItem
        }
    }

    class HeaderViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        val binding = ItemPlaceContentHeaderBinding.bind(itemView)

        companion object {

            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_place_content_header, parent, false)
                return HeaderViewHolder(view)
            }
        }
    }

    class AnimalViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        val binding = ItemPlaceContentAnimalBinding.bind(itemView)

        companion object {

            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_place_content_animal, parent, false)
                return AnimalViewHolder(view)
            }
        }
    }

    interface ItemClickListener {
        fun onWebsiteClick(url: String)
        fun onItemClick(data: GetAnimalsResponse.Result.Animal)
    }
}