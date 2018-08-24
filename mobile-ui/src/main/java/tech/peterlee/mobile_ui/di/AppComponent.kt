package tech.peterlee.mobile_ui.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import tech.peterlee.mobile_ui.GithubTrendingApplication
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: GithubTrendingApplication)
}