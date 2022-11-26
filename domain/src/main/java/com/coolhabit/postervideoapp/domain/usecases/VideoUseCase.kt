package com.coolhabit.postervideoapp.domain.usecases

import com.coolhabit.postervideoapp.domain.api.IVideoItemsApiService
import com.coolhabit.postervideoapp.domain.entities.VideoItem
import javax.inject.Inject

class VideoUseCase @Inject constructor(
    private val service: IVideoItemsApiService,
) {

    suspend fun loadVideoList(): List<VideoItem> {
        return service.loadVideoItemsList()
    }
}
