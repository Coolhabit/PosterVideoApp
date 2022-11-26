package com.coolhabit.postervideoapp.video.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.coolhabit.postervideoapp.domain.entities.VideoItem
import com.coolhabit.postervideoapp.video.databinding.RvPosterItemBinding
import javax.inject.Inject

class VideoPosterAdapter @Inject constructor() : ListAdapter<VideoItem, VideoPosterViewHolder>(VideoPosterDiffUtils()) {

    var onClick: (String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoPosterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvPosterItemBinding.inflate(inflater, parent, false)
        return VideoPosterViewHolder(binding) { pos ->
            onClick(getItem(pos).url)
        }
    }

    override fun onBindViewHolder(holder: VideoPosterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class VideoPosterDiffUtils: DiffUtil.ItemCallback<VideoItem>() {

        override fun areItemsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
            return oldItem == newItem
        }
    }
}
