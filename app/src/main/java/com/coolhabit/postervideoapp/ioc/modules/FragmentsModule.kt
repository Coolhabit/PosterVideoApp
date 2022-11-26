package com.coolhabit.postervideoapp.ioc.modules

import com.coolhabit.postervideoapp.video.presentation.VideoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class, ActivityModule::class])
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun provideVideoFragment(): VideoFragment
}
