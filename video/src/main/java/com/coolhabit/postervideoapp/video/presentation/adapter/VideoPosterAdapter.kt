package com.coolhabit.postervideoapp.video.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.coolhabit.postervideoapp.video.databinding.RvPosterItemBinding
import com.coolhabit.postervideoapp.video.presentation.model.VideoItemUI
import javax.inject.Inject

class VideoPosterAdapter @Inject constructor() :
    ListAdapter<VideoItemUI, VideoPosterViewHolder>(VideoPosterDiffUtils()) {

    var onClick: (String, String) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoPosterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvPosterItemBinding.inflate(inflater, parent, false)
        return VideoPosterViewHolder(binding) { position, _ ->
            onClick(getItem(position).id, getItem(position).url)
        }
    }

    override fun onBindViewHolder(holder: VideoPosterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class VideoPosterDiffUtils : DiffUtil.ItemCallback<VideoItemUI>() {

        override fun areItemsTheSame(oldItem: VideoItemUI, newItem: VideoItemUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: VideoItemUI, newItem: VideoItemUI): Boolean {
            return oldItem == newItem
        }
    }
}
