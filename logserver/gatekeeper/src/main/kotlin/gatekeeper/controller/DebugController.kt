package gatekeeper.controller

import gatekeeper.model.searchguard.SearchguardRoleConfig
import gatekeeper.model.searchguard.SearchguardUserProperties
import gatekeeper.service.keycloak.KeycloakApi
import gatekeeper.service.searchGuard.PermissionFileWriter
import gatekeeper.service.searchGuard.PermissionSyncService
import gatekeeper.service.searchGuard.PermissionsFileReader
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import javax.inject.Inject

@Controller("/test")
class DebugController(
        @Inject val api: KeycloakApi,
        @Inject val permissionSyncService: PermissionSyncService
) {

    @Get("/key")
    fun getGroups(){
        api.retrieveGroups()
    }

    @Get("/users")
    fun getUsers(): Map<String, SearchguardUserProperties> {
        val readUsers = PermissionsFileReader.readUsers()
        PermissionFileWriter.writeUsers(readUsers)
        return readUsers
    }

    @Get("/roles")
    fun getRoles(): Map<String, SearchguardRoleConfig> {
        return PermissionsFileReader.readRoles()
    }

    @Get("sync")
    fun sync() {
        permissionSyncService.syncRolesWithKeycloak()
    }
}