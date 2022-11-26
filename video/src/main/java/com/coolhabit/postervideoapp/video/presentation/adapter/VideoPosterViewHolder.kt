package com.coolhabit.postervideoapp.video.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.coolhabit.postervideoapp.baseUI.extensions.load
import com.coolhabit.postervideoapp.domain.entities.VideoItem
import com.coolhabit.postervideoapp.video.databinding.RvPosterItemBinding

class VideoPosterViewHolder(
    private val binding: RvPosterItemBinding,
    private val onClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener { onClick.invoke(bindingAdapterPosition) }
    }

    fun bind(item: VideoItem) {
        binding.poster.load(item.poster) {
            it.centerCrop()
        }
    }
}
