package com.example.intershiptask.screens.items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.intershiptask.databinding.ItemElementBinding
import com.example.intershiptask.screens.entity.Item

class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.VH>() {
    private val items = Item.getItems()
    var onItemClick: ((Item) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemElementBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        with(holder.binding){
            tvName.text = item.name
            root.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }

    inner class VH(val binding: ItemElementBinding) : RecyclerView.ViewHolder(binding.root)
}