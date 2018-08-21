package tech.peterlee.domain.interactor.browse

import io.reactivex.Observable
import tech.peterlee.domain.executor.PostExecutionThread
import tech.peterlee.domain.interactor.ObservableUseCase
import tech.peterlee.domain.model.Project
import tech.peterlee.domain.repository.ProjectsRepository
import javax.inject.Inject

class GetProjects @Inject constructor(
        private val projectsRepository: ProjectsRepository,
        postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Project>, Nothing>(postExecutionThread) {

    public override fun createObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getProjects()
    }
}