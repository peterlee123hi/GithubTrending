package tech.peterlee.mobile_ui.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import tech.peterlee.domain.repository.ProjectsRepository
import tech.peterlee.mobile_ui.TestApplication
import tech.peterlee.mobile_ui.di.module.PresentationModule
import tech.peterlee.mobile_ui.di.module.UiModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class,
        TestAppModule::class,
        TestCacheModule::class,
        TestDataModule::class,
        PresentationModule::class,
        UiModule::class,
        TestRemoteModule::class))
interface TestAppComponent {

    fun projectsRepository(): ProjectsRepository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): TestAppComponent.Builder

        fun build(): TestAppComponent
    }

    fun inject(application: TestApplication)
}