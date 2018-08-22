package tech.peterlee.data.repository

import io.reactivex.Observable
import tech.peterlee.data.model.ProjectEntity

interface ProjectsRemote {

    fun getProjects(): Observable<List<ProjectEntity>>
}