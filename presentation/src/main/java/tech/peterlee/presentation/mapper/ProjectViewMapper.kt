package tech.peterlee.presentation.mapper

import tech.peterlee.domain.model.Project
import tech.peterlee.presentation.model.ProjectView
import javax.inject.Inject

class ProjectViewMapper @Inject constructor(): Mapper<ProjectView, Project> {

    override fun mapToView(type: Project): ProjectView {
        return ProjectView(type.id, type.name, type.fullName,
                type.starCount, type.dateCreated, type.ownerName,
                type.ownerAvatar, type.isBookmarked)
    }
}