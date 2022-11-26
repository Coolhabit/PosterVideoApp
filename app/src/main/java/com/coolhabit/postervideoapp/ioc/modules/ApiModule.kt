package com.coolhabit.postervideoapp.ioc.modules

import com.coolhabit.postervideoapp.data.network.VideoApi
import com.coolhabit.postervideoapp.data.network.services.VideoItemsService
import com.coolhabit.postervideoapp.domain.api.IVideoItemsApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideVideoApiService(api: VideoApi): IVideoItemsApiService = VideoItemsService(api)
}
