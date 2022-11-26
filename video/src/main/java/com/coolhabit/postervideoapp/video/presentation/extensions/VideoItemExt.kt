package com.coolhabit.postervideoapp.video.presentation.extensions

import com.coolhabit.postervideoapp.domain.entities.VideoItem
import com.coolhabit.postervideoapp.video.presentation.model.VideoItemUI

fun VideoItem.toPresentation(selectedId: String?): VideoItemUI {
    return VideoItemUI(
        id = this.id,
        url = this.url,
        poster = this.poster,
        isSelected = id == selectedId
    )
}
