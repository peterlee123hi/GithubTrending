package tech.peterlee.mobile_ui.di

import android.app.Application
import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import dagger.Provides
import tech.peterlee.cache.db.ProjectsDatabase
import tech.peterlee.data.repository.ProjectsCache

@Module
object TestCacheModule {

    @Provides
    @JvmStatic
    fun provideDatabase(application: Application): ProjectsDatabase {
        return ProjectsDatabase.getInstance(application)
    }

    @Provides
    @JvmStatic
    fun provideProjectsCache(): ProjectsCache {
        return mock()
    }

}