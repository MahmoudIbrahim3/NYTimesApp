package com.nytimesapp.di.module

import com.nytimesapp.presentation.ui.articledetails.ArticleDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeArticleDetailFragment(): ArticleDetailFragment
}