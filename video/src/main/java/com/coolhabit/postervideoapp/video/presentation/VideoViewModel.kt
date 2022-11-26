package com.coolhabit.postervideoapp.video.presentation

import com.coolhabit.postervideoapp.baseUI.model.StatefulData
import com.coolhabit.postervideoapp.baseUI.presentation.BaseViewModel
import com.coolhabit.postervideoapp.domain.entities.VideoItem
import com.coolhabit.postervideoapp.domain.usecases.VideoUseCase
import com.coolhabit.postervideoapp.video.presentation.extensions.toPresentation
import com.coolhabit.postervideoapp.video.presentation.model.VideoItemUI
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VideoViewModel @Inject constructor(
    private val useCase: VideoUseCase,
) : BaseViewModel() {

    private val _loadVideo = statefulSharedFlow<List<VideoItemUI>>()
    val loadVideo: Flow<StatefulData<List<VideoItemUI>>>
        get() = _loadVideo

    private var _savedData: List<VideoItem>? = null
    val savedData: List<VideoItem>
    get() = _savedData!!

    fun initContent() {
        _loadVideo.fetch {
            val newData = useCase.loadVideoList()
            _savedData = newData
            newData.map { it.toPresentation(null) }
        }
    }

    fun refreshSelected(selectedId: String) {
        _loadVideo.fetch {
            savedData.map { it.toPresentation(selectedId) }
        }
    }
}
