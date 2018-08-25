package tech.peterlee.mobile_ui.di.module

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import tech.peterlee.cache.ProjectsCacheImpl
import tech.peterlee.cache.db.ProjectsDatabase
import tech.peterlee.data.repository.ProjectsCache

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: Application): ProjectsDatabase {
            return ProjectsDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindProjectsCache(projectsCache: ProjectsCacheImpl): ProjectsCache
}