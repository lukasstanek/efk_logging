package gatekeeper.controller

import gatekeeper.model.CreateProjectRequest
import gatekeeper.service.ProjectService
import javax.inject.Inject

class ProjectController(@Inject val projectService: ProjectService) {

    fun createProject(project: CreateProjectRequest){
        projectService.addProject(project)
    }
}