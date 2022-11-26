package com.coolhabit.postervideoapp.ioc.modules

import android.content.Context
import com.coolhabit.postervideoapp.PosterVideoApp
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideContext(app: PosterVideoApp): Context = app.applicationContext
}
