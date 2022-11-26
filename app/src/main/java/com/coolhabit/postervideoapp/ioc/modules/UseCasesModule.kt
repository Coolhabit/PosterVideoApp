package com.coolhabit.postervideoapp.ioc.modules

import com.coolhabit.postervideoapp.domain.api.IVideoItemsApiService
import com.coolhabit.postervideoapp.domain.usecases.VideoUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCasesModule {

    @Provides
    @Singleton
    fun provideVideoUseCase(
        service: IVideoItemsApiService,
    ) = VideoUseCase(service)
}
