package com.example.taskn26.presentation.screen.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskn26.databinding.DotRecyclerViewItemBinding
import com.example.taskn26.presentation.model.Dot

class DotsRecyclerViewAdapter :
    ListAdapter<Dot, DotsRecyclerViewAdapter.DotViewHolder>(dotsDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DotViewHolder {
        return DotViewHolder(DotRecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DotViewHolder, position: Int) {}

    inner class DotViewHolder(binding: DotRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private val dotsDiffCallback = object : DiffUtil.ItemCallback<Dot>() {

            override fun areItemsTheSame(oldItem: Dot, newItem: Dot): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Dot, newItem: Dot): Boolean {
                return oldItem == newItem
            }
        }
    }
}