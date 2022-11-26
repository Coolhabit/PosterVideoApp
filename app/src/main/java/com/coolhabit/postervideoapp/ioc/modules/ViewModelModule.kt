package com.coolhabit.postervideoapp.ioc.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coolhabit.postervideoapp.ioc.utils.ViewModelFactory
import com.coolhabit.postervideoapp.ioc.utils.ViewModelKey
import com.coolhabit.postervideoapp.presentation.MainActivityViewModel
import com.coolhabit.postervideoapp.video.presentation.VideoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun mainActivityViewModel(viewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VideoViewModel::class)
    abstract fun videoViewModel(viewModel: VideoViewModel): ViewModel
}
