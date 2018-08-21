package tech.peterlee.domain.interactor.bookmark

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import tech.peterlee.domain.executor.PostExecutionThread
import tech.peterlee.domain.repository.ProjectsRepository
import tech.peterlee.domain.test.ProjectDataFactory

class UnbookmarkProjectsTest {

    private lateinit var unbookmarkProject: UnbookmarkProject
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        unbookmarkProject = UnbookmarkProject(projectsRepository, postExecutionThread)
    }

    @Test
    fun unbookmarkProjectCompletes() {
        val project = ProjectDataFactory.makeProject()
        stubUnbookmarkProject(Completable.complete())
        val testObserver = unbookmarkProject.createCompletable(
                UnbookmarkProject.Params.forProject(project.id)
        ).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun unbookmarkProjectThrowsException() {
        unbookmarkProject.createCompletable().test()
    }

    private fun stubUnbookmarkProject(completable: Completable) {
        whenever(projectsRepository.unbookmarkProject(any()))
                .thenReturn(completable)
    }
}