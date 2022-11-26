package com.coolhabit.postervideoapp.video.presentation

import com.coolhabit.postervideoapp.baseUI.model.StatefulData
import com.coolhabit.postervideoapp.baseUI.presentation.BaseViewModel
import com.coolhabit.postervideoapp.domain.entities.VideoItem
import com.coolhabit.postervideoapp.domain.usecases.VideoUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VideoViewModel @Inject constructor(
    private val useCase: VideoUseCase,
) : BaseViewModel() {

    private val _loadVideo = statefulSharedFlow<List<VideoItem>>()
    val loadVideo: Flow<StatefulData<List<VideoItem>>>
        get() = _loadVideo

    fun initContent() {
        _loadVideo.fetch {
            useCase.loadVideoList()
        }
    }
}
