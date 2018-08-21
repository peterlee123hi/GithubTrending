package tech.peterlee.domain.interactor.bookmark

import io.reactivex.Completable
import tech.peterlee.domain.executor.PostExecutionThread
import tech.peterlee.domain.interactor.CompletableUseCase
import tech.peterlee.domain.repository.ProjectsRepository
import java.lang.IllegalArgumentException
import javax.inject.Inject

class BookmarkProject @Inject constructor(
        private val projectsRepository: ProjectsRepository,
        postExecutionThread: PostExecutionThread
) : CompletableUseCase<BookmarkProject.Params>(postExecutionThread) {

    public override fun createCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Found null parameter argument")
        return projectsRepository.bookmarkProject(params.projectId)
    }

    data class Params constructor(val projectId: String) {
        companion object {
            fun forProject(projectId: String): Params {
                return Params(projectId)
            }
        }
    }
}