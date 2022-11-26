package com.coolhabit.postervideoapp.video.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.coolhabit.postervideoapp.baseUI.extensions.load
import com.coolhabit.postervideoapp.video.databinding.RvPosterItemBinding
import com.coolhabit.postervideoapp.video.presentation.model.VideoItemUI
import com.google.android.material.imageview.ShapeableImageView

class VideoPosterViewHolder(
    private val binding: RvPosterItemBinding,
    private val onClick: (Int, Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener { onClick.invoke(bindingAdapterPosition, bindingAdapterPosition) }
    }

    fun bind(item: VideoItemUI) {
        binding.poster.load(item.poster) {
            it.centerCrop()
        }
        binding.poster.selectCheck(item.isSelected)
    }

    private fun ShapeableImageView.selectCheck(isSelected: Boolean) {
        if (isSelected) {
            this.setStrokeWidthResource(com.coolhabit.postervideoapp.baseUI.R.dimen.size_2)
        } else {
            this.setStrokeWidthResource(com.coolhabit.postervideoapp.baseUI.R.dimen.size_0)
        }
    }
}
