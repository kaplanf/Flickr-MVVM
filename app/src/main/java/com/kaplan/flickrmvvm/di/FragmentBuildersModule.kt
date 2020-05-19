package com.kaplan.flickrmvvm.di



import com.kaplan.flickrmvvm.recent.ui.DetailFragment
import com.kaplan.flickrmvvm.recent.ui.RecentFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
  @ContributesAndroidInjector
  abstract fun contributeRecentFragment(): RecentFragment

  @ContributesAndroidInjector
  abstract fun contributeDetailFragment(): DetailFragment
}
