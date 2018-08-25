package tech.peterlee.mobile_ui.di.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import tech.peterlee.data.repository.ProjectsRemote
import tech.peterlee.mobile_ui.BuildConfig
import tech.peterlee.remote.ProjectsRemoteImpl
import tech.peterlee.remote.service.GithubTrendingServiceFactory
import tech.peterlee.remote.service.GithubTrendingService

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideGithubService(): GithubTrendingService {
            return GithubTrendingServiceFactory.makeGithubTrendingService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindProjectsRemote(projectsRemote: ProjectsRemoteImpl): ProjectsRemote
}