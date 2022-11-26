package com.coolhabit.postervideoapp.ioc.modules

import com.coolhabit.postervideoapp.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun mainActivity(): MainActivity
}
