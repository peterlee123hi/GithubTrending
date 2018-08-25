package tech.peterlee.mobile_ui.di.module

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import tech.peterlee.domain.executor.PostExecutionThread
import tech.peterlee.mobile_ui.UiThread
import tech.peterlee.mobile_ui.browse.BrowseActivity

@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesBrowseActivity(): BrowseActivity
}