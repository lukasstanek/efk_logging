package gatekeeper.init

import gatekeeper.constants.Constants.CERTS_PATH
import gatekeeper.service.searchGuard.CertificateService
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.runtime.server.event.ServerStartupEvent
import io.micronaut.scheduling.annotation.Async
import java.io.File
import java.io.FilenameFilter
import javax.inject.Singleton


@Singleton
class InitialCertGenerator(
        private val certificateService: CertificateService
): ApplicationEventListener<ServerStartupEvent> {
    override fun onApplicationEvent(event: ServerStartupEvent?) {
        File(CERTS_PATH).list {
            dir, name ->
            name != ".gitkeep"
        }.ifEmpty {
            certificateService.generateCerts()
        }
    }
}