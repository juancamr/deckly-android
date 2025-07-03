package com.molerocn.deckly.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.molerocn.deckly.databinding.ItemDeckBinding
import com.molerocn.deckly.domain.model.Deck

class DeckAdapter(
    private val onItemClick: (Deck) -> Unit
) : ListAdapter<Deck, DeckAdapter.MyDataViewHolder>(DiffCallback()) {

    inner class MyDataViewHolder(private val binding: ItemDeckBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Deck) {
            binding.textName.text = item.name

            // Acción al hacer clic
            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDataViewHolder {
        val binding = ItemDeckBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyDataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyDataViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Deck>() {
        override fun areItemsTheSame(oldItem: Deck, newItem: Deck) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Deck, newItem: Deck) = oldItem == newItem
    }
}
