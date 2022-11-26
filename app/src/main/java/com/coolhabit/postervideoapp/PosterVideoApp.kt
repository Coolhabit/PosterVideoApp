package com.coolhabit.postervideoapp

import com.coolhabit.postervideoapp.ioc.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class PosterVideoApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent
            .factory()
            .create(this)
    }
}
