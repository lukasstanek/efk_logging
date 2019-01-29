package gatekeeper.service

import gatekeeper.model.Project

class ProjectService {
    val projects: MutableList<Project> = mutableListOf()

    fun addProject(project: Project) {
        if(projects.none{ it == project }){
            projects.add(project)
        }
    }
}