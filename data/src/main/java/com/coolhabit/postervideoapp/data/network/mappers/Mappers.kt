package com.coolhabit.postervideoapp.data.network.mappers

import com.coolhabit.postervideoapp.data.network.entities.NetworkVideoItem
import com.coolhabit.postervideoapp.domain.entities.VideoItem

fun NetworkVideoItem.toDomain(): VideoItem {
    return VideoItem(
        id = this.id,
        url = this.file_url,
        poster = this.small_thumbnail_url,
    )
}
