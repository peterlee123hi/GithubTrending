package tech.peterlee.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import tech.peterlee.domain.interactor.bookmark.BookmarkProject
import tech.peterlee.domain.interactor.bookmark.UnbookmarkProject
import tech.peterlee.domain.interactor.browse.GetProjects
import tech.peterlee.domain.model.Project
import tech.peterlee.presentation.mapper.ProjectViewMapper
import tech.peterlee.presentation.model.ProjectView
import tech.peterlee.presentation.state.Resource
import tech.peterlee.presentation.state.ResourceState
import javax.inject.Inject

class BrowseProjectsViewModel @Inject internal constructor(
        private val getProjects: GetProjects,
        private val mapper: ProjectViewMapper,
        private val bookmarkProject: BookmarkProject,
        private val unbookmarkProject: UnbookmarkProject
): ViewModel() {

    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()

    init {
        fetchProjects()
    }

    override fun onCleared() {
        super.onCleared()
        getProjects.dispose()
    }

    fun getProjects(): LiveData<Resource<List<ProjectView>>> {
        return liveData
    }

    fun fetchProjects() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getProjects.execute(ProjectsSubscriber())
    }

    fun bookmarkProject(projectId: String) {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return bookmarkProject.execute(BookmarkProjectsSubscriber(),
                BookmarkProject.Params.forProject(projectId))
    }

    fun unbookmarkProject(projectId: String) {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return unbookmarkProject.execute(BookmarkProjectsSubscriber(),
                UnbookmarkProject.Params.forProject(projectId))
    }

    inner class ProjectsSubscriber: DisposableObserver<List<Project>>() {
        override fun onNext(t: List<Project>) {
            liveData.postValue(Resource(ResourceState.SUCCESS,
                    t.map { mapper.mapToView(it) }, null))
        }

        override fun onComplete() { }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }

    inner class BookmarkProjectsSubscriber: DisposableCompletableObserver() {
        override fun onComplete() {
            liveData.postValue(Resource(ResourceState.SUCCESS, liveData.value?.data, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, liveData.value?.data,
                    e.localizedMessage))
        }
    }
}