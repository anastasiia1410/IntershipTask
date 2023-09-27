package com.example.intershiptask.screens.items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.intershiptask.databinding.ItemElementBinding
import com.example.intershiptask.screens.entity.Item

class ItemsAdapter : ListAdapter<Item, ItemsAdapter.VH>(DiffCallback()) {
    var onItemClick: ((item: Item) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemElementBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        holder.bind(item, onItemClick)
    }

    class VH(private val binding: ItemElementBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item, onItemClick: ((item: Item) -> Unit)?) {
            with(binding) {
                tvName.text = item.name
                root.setOnClickListener {
                    onItemClick?.invoke(item)
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Item, newItem: Item) =
            oldItem == newItem
    }
}