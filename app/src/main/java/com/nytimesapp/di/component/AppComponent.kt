package com.devo.mahmoud.di.component

import android.app.Application
import com.nytimesapp.NYTimesApplication
import com.nytimesapp.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    ApiModule::class,
    AppModule::class,
    ViewModelModule::class,
    ActivityModule::class,
    FragmentModule::class,
    InteractorsModule::class
])

interface AppComponent : AndroidInjector<NYTimesApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(NYTimesApplication: NYTimesApplication)
}