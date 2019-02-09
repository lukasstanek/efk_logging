package gatekeeper.service.searchGuard

import gatekeeper.constants.Constants.CURRENT_CONFIG_PATH
import gatekeeper.file.YAMLFileIOService
import gatekeeper.model.searchguard.SearchguardRoleConfig
import gatekeeper.model.searchguard.SearchguardUserProperties
import gatekeeper.service.searchGuard.SearguardConfigFiles.ROLES_CONFIG
import gatekeeper.service.searchGuard.SearguardConfigFiles.USER_CONFIG


object SearguardConfigFiles {
    const val ROLES_CONFIG = "sg_roles.yml"
    const val USER_CONFIG = "sg_internal_users.yml"
    const val ROLE_MAPPING_CONFIG = "sg_roles_mapping.yml"
}

object PermissionsFileReader{
    fun readUsers(path: String = CURRENT_CONFIG_PATH): Map<String, SearchguardUserProperties>{
        return YAMLFileIOService.parseToMap("$path/$USER_CONFIG")
    }

    fun readRoles(path: String = CURRENT_CONFIG_PATH): Map<String, SearchguardRoleConfig>{
        return YAMLFileIOService.parseToMap("$path/$ROLES_CONFIG")
    }


}

object PermissionFileWriter{
    fun writeUsers(users: Map<String, SearchguardUserProperties>){
        writeToCurrentConfig(USER_CONFIG, users)
    }

    fun writeRoles(roles: Map<String, SearchguardRoleConfig>){
        writeToCurrentConfig(ROLES_CONFIG, roles)
    }

    private fun writeToCurrentConfig(file: String, data: Any){
        YAMLFileIOService.writeToFile("$CURRENT_CONFIG_PATH/$file", data)
    }
}