package tech.peterlee.remote

import io.reactivex.Flowable
import tech.peterlee.data.model.ProjectEntity
import tech.peterlee.data.repository.ProjectsRemote
import tech.peterlee.remote.mapper.ProjectsResponseModelMapper
import tech.peterlee.remote.service.GithubTrendingService
import javax.inject.Inject

class ProjectsRemoteImpl @Inject constructor(
        private val service: GithubTrendingService,
        private val mapper: ProjectsResponseModelMapper
): ProjectsRemote {

    override fun getProjects(): Flowable<List<ProjectEntity>> {
        return service.searchRepositories("language:kotlin", "stars", "desc")
                .map {
                    it.items.map{ mapper.mapFromModel(it) }
                }
    }
}