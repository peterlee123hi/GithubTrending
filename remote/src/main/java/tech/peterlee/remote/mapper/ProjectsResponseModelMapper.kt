package tech.peterlee.remote.mapper

import tech.peterlee.data.model.ProjectEntity
import tech.peterlee.remote.model.ProjectModel

class ProjectsResponseModelMapper: ModelMapper<ProjectModel, ProjectEntity> {

    override fun mapFromModel(model: ProjectModel): ProjectEntity {
        return ProjectEntity(model.id, model.name, model.fullName, model.starCount.toString(),
                model.dateCreated, model.owner.ownerName, model.owner.ownerAvatar, false)
    }
}