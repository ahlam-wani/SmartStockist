package com.smartstockist.app.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.smartstockist.app.data.model.Item
import com.smartstockist.app.databinding.NavigationItemBinding

class DrawerAdapter(
    private val drawerItems: List<Item>,
    private val listener: AdapterView.OnItemClickListener
) : RecyclerView.Adapter<DrawerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NavigationItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(drawerItems[position], View.OnClickListener {
            listener.onItemClick(null,null,position,0L)
        })
    }

    override fun getItemCount(): Int {
        return drawerItems.size
    }

    class ViewHolder(val binding: NavigationItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: Item, listener: View.OnClickListener) {
            binding.tvTitle.text = item.name
            binding.tvTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(item.res, 0, 0, 0)
            binding.rootView.setOnClickListener(listener)
        }
    }
}