package com.coolhabit.postervideoapp.domain.api

import com.coolhabit.postervideoapp.domain.entities.VideoItem

interface IVideoItemsApiService {

    suspend fun loadVideoItemsList(): List<VideoItem>
}
