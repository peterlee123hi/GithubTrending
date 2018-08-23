package tech.peterlee.data.store

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import tech.peterlee.data.model.ProjectEntity
import tech.peterlee.data.repository.ProjectsCache
import tech.peterlee.data.test.factory.ProjectFactory

@RunWith(JUnit4::class)
class ProjectsCacheDataStoreTest {

    private val cache = mock<ProjectsCache>()
    private val store = ProjectsCacheDataStore(cache)

    @Test
    fun getProjectsCompletes() {
        stubProjectsCacheGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        val testObserver = store.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubProjectsCacheGetProjects(Observable.just(data))
        val testObserver = store.getProjects().test()
        testObserver.assertValue(data)
    }

    @Test
    fun getProjectsCallsCacheSource() {
        stubProjectsCacheGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        store.getProjects().test()
        verify(cache).getProjects()
    }

    @Test
    fun saveProjectsCompletes() {
        stubProjectsCacheSaveProjects(Completable.complete())
        stubProjectsCacheSetLastCacheTime(Completable.complete())
        val testObserver = store.saveProjects(listOf(ProjectFactory.makeProjectEntity())).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveProjectsCallsCache() {
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubProjectsCacheSaveProjects(Completable.complete())
        stubProjectsCacheSetLastCacheTime(Completable.complete())
        store.saveProjects(data).test()
        verify(cache).saveProjects(data)
    }

    @Test
    fun clearProjectsCompletes() {
        stubProjectsCacheClearProjects(Completable.complete())
        val testObserver = store.clearProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun clearProjectsCallsCache() {
        stubProjectsCacheClearProjects(Completable.complete())
        store.clearProjects().test()
        verify(cache).clearProjects()
    }

    @Test
    fun getBookmarkedProjectsCompletes() {
        stubProjectsCacheGetBookmarkedProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        val testObserver = store.getBookmarkedProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBookmarkedProjectsCallsCache() {
        stubProjectsCacheGetBookmarkedProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        store.getBookmarkedProjects().test()
        verify(cache).getBookmarkedProjects()
    }

    @Test
    fun getBookmarkedProjectsReturnsData() {
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubProjectsCacheGetBookmarkedProjects(Observable.just(data))
        val testObserver = store.getBookmarkedProjects().test()
        testObserver.assertValue(data)
    }

    @Test
    fun setProjectAsBookmarkedCompletes() {
        val data = ProjectFactory.makeProjectEntity()
        stubProjectsCacheSetProjectAsBookmarked(Completable.complete())
        val testObserver = store.setProjectAsBookmarked(data.id).test()
        testObserver.assertComplete()
    }

    @Test
    fun setProjectAsBookmarkedCallsCache() {
        val data = ProjectFactory.makeProjectEntity()
        stubProjectsCacheSetProjectAsBookmarked(Completable.complete())
        store.setProjectAsBookmarked(data.id).test()
        verify(cache).setProjectAsBookmarked(data.id)
    }

    @Test
    fun setProjectAsNotBookmarkedCompletes() {
        val data = ProjectFactory.makeProjectEntity()
        stubProjectsCacheSetProjectAsNotBookmarked(Completable.complete())
        val testObserver = store.setProjectAsNotBookmarked(data.id).test()
        testObserver.assertComplete()
    }

    @Test
    fun setProjectAsNotBookmarkedCallsCache() {
        val data = ProjectFactory.makeProjectEntity()
        stubProjectsCacheSetProjectAsNotBookmarked(Completable.complete())
        store.setProjectAsNotBookmarked(data.id).test()
        verify(cache).setProjectAsNotBookmarked(data.id)
    }

    private fun stubProjectsCacheGetProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(cache.getProjects())
                .thenReturn(observable)
    }

    private fun stubProjectsCacheSaveProjects(completable: Completable) {
        whenever(cache.saveProjects(any()))
                .thenReturn(completable)
    }

    private fun stubProjectsCacheSetLastCacheTime(completable: Completable) {
        whenever(cache.setLastCacheTime(any()))
                .thenReturn(completable)
    }

    private fun stubProjectsCacheClearProjects(completable: Completable) {
        whenever(cache.clearProjects())
                .thenReturn(completable)
    }

    private fun stubProjectsCacheGetBookmarkedProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(cache.getBookmarkedProjects())
                .thenReturn(observable)
    }

    private fun stubProjectsCacheSetProjectAsBookmarked(completable: Completable) {
        whenever(cache.setProjectAsBookmarked(any()))
                .thenReturn(completable)
    }

    private fun stubProjectsCacheSetProjectAsNotBookmarked(completable: Completable) {
        whenever(cache.setProjectAsNotBookmarked(any()))
                .thenReturn(completable)
    }
}