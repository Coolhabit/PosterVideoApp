package com.coolhabit.postervideoapp.data.network.services

import com.coolhabit.postervideoapp.data.network.VideoApi
import com.coolhabit.postervideoapp.data.network.mappers.toDomain
import com.coolhabit.postervideoapp.domain.api.IVideoItemsApiService
import com.coolhabit.postervideoapp.domain.entities.VideoItem

class VideoItemsService(private val api: VideoApi) : IVideoItemsApiService {

    override suspend fun loadVideoItemsList(): List<VideoItem> {
        return api.getVideoItems().map {
            it.toDomain()
        }
    }
}
