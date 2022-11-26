package com.coolhabit.postervideoapp.ioc

import com.coolhabit.postervideoapp.ioc.modules.ActivityModule
import com.coolhabit.postervideoapp.ioc.modules.ApiModule
import com.coolhabit.postervideoapp.ioc.modules.ApplicationModule
import com.coolhabit.postervideoapp.ioc.modules.FragmentsModule
import com.coolhabit.postervideoapp.ioc.modules.NavigationRoutersModule
import com.coolhabit.postervideoapp.ioc.modules.StoragesModule
import com.coolhabit.postervideoapp.ioc.modules.UseCasesModule
import com.coolhabit.postervideoapp.ioc.modules.ViewModelModule
import com.coolhabit.postervideoapp.PosterVideoApp
import com.coolhabit.postervideoapp.data.ioc.RemoteModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        ApplicationModule::class,
        FragmentsModule::class,
        UseCasesModule::class,
        NavigationRoutersModule::class,
        ApiModule::class,
        RemoteModule::class,
        StoragesModule::class,
    ]
)
interface ApplicationComponent : AndroidInjector<PosterVideoApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: PosterVideoApp): ApplicationComponent
    }
}
