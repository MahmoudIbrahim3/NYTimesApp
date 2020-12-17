package com.nytimesapp.di.module

import com.nytimesapp.presentation.MainActivity
import com.nytimesapp.presentation.ui.articles.ArticlesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity
}