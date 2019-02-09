package gatekeeper.init

import gatekeeper.constants.Constants
import gatekeeper.constants.Constants.DEFAULT_CONFIG_PATH
import gatekeeper.log.LoggerDelegate
import gatekeeper.service.searchGuard.PermissionFileWriter
import gatekeeper.service.searchGuard.PermissionsFileReader
import gatekeeper.service.searchGuard.SearchguardSyncService
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.runtime.server.event.ServerStartupEvent
import io.micronaut.scheduling.annotation.Async
import java.io.File
import javax.inject.Singleton


@Singleton
class InitialPermissionUpdater(
        private val searchguardSyncService: SearchguardSyncService
): ApplicationEventListener<ServerStartupEvent> {
    companion object {
        val logger by LoggerDelegate()
    }

    override fun onApplicationEvent(event: ServerStartupEvent?) {
        File(Constants.CURRENT_CONFIG_PATH).list {
            dir, name ->
            name != ".gitkeep"
        }.ifEmpty {
            logger.info("Copying default config into current")
            File(Constants.DEFAULT_CONFIG_PATH).copyRecursively(File(Constants.CURRENT_CONFIG_PATH))
            logger.info("Done copying")
        }
        searchguardSyncService.updateESClusterPermissions()
    }

}