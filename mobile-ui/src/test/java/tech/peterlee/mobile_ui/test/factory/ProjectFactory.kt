package tech.peterlee.mobile_ui.test.factory

import tech.peterlee.presentation.model.ProjectView

object ProjectFactory {

    fun makeProjectView(): ProjectView {
        return ProjectView(DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomBoolean())
    }
}