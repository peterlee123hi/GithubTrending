package tech.peterlee.mobile_ui.di

import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import dagger.Provides
import tech.peterlee.data.repository.ProjectsRemote
import tech.peterlee.remote.service.GithubTrendingService

@Module
object TestRemoteModule {

    @Provides
    @JvmStatic
    fun provideGithubService(): GithubTrendingService {
        return mock()
    }

    @Provides
    @JvmStatic
    fun provideProjectsRemote(): ProjectsRemote {
        return mock()
    }
}