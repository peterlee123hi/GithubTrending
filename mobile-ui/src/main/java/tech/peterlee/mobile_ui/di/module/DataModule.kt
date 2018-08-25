package tech.peterlee.mobile_ui.di.module

import dagger.Binds
import dagger.Module
import tech.peterlee.data.ProjectsDataRepository
import tech.peterlee.domain.repository.ProjectsRepository

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: ProjectsDataRepository): ProjectsRepository
}