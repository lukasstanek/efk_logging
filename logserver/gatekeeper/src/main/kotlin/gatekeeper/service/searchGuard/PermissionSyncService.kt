package gatekeeper.service.searchGuard

import gatekeeper.model.searchguard.generateLoggroupConfig
import gatekeeper.service.keycloak.KeycloakApi
import javax.inject.Singleton

@Singleton
class PermissionSyncService(
        private val keycloakApi: KeycloakApi
) {
    companion object {
        const val LOGGROUP_PREFIX = "loggroup"
    }

    fun syncRolesWithKeycloak(){
        val localRoles = PermissionsFileReader.readRoles().filterKeys {
            !it.startsWith("${LOGGROUP_PREFIX}_")
        }.toMutableMap()

        keycloakApi.retrieveGroups().forEach{
            val roleConfig = generateLoggroupConfig(it.name)
            localRoles["${LOGGROUP_PREFIX}_${it.name}"] = roleConfig
        }

        PermissionFileWriter.writeRoles(localRoles)
    }

}