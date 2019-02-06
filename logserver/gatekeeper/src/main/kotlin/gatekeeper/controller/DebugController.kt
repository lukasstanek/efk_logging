package gatekeeper.controller

import gatekeeper.service.keycloak.KeycloakSyncService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import javax.inject.Inject

@Controller("/test")
class DebugController(
        @Inject val syncService: KeycloakSyncService
) {

    @Get("/key")
    fun getGroups(){
        syncService.retrieveGroups()
    }
}