package com.coolhabit.postervideoapp.video.presentation.model

data class VideoItemUI(
    val id: String,
    val url: String,
    val poster: String,
    var isSelected: Boolean,
)
