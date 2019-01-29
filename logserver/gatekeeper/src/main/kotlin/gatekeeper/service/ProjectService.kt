package gatekeeper.service

import gatekeeper.model.CreateProjectRequest
import gatekeeper.model.Project
import gatekeeper.util.PasswordHasher

class ProjectService {
    private val projects: MutableList<Project> = mutableListOf()

    fun addProject(projectRequest: CreateProjectRequest) {
        val project = Project(
                name = projectRequest.name,
                indexPrefix = projectRequest.name.toLowerCase().replace(" ", "-"),
                passwordHash = PasswordHasher.hashPw(projectRequest.password)
        )

        if(projects.none{ it.indexPrefix == project.indexPrefix }){
            projects.add(project)
        }
    }
}