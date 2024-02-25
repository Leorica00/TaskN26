package com.example.taskn26.presentation.screen.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskn26.databinding.SearchItemRecyclerViewBinding
import com.example.taskn26.presentation.model.Dot
import com.example.taskn26.presentation.model.SearchItem

class SearchFilterRecyclerViewAdapter :
    ListAdapter<SearchItem, SearchFilterRecyclerViewAdapter.SearchItemsViewHolder>(filterDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemsViewHolder {
        return SearchItemsViewHolder(SearchItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SearchItemsViewHolder, position: Int) {
        holder.bind()
    }

    inner class SearchItemsViewHolder(private val binding: SearchItemRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = currentList[adapterPosition]
            with(binding) {
                if (item.numberOfChildren > 0) {
                    recyclerViewDots.visibility = View.VISIBLE
                    recyclerViewDots.layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
                    recyclerViewDots.adapter = DotsRecyclerViewAdapter().apply {
                        val dots = mutableListOf<Dot>()
                        for (i in 1..item.numberOfChildren)
                            dots.add(Dot(i))
                        submitList(dots)
                    }
                }

                tvName.text = item.name
            }
        }
    }

    companion object {
        private val filterDiffCallback = object : DiffUtil.ItemCallback<SearchItem>() {

            override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}