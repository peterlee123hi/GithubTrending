package tech.peterlee.data.repository

import io.reactivex.Flowable
import tech.peterlee.data.model.ProjectEntity

interface ProjectsRemote {

    fun getProjects(): Flowable<List<ProjectEntity>>
}