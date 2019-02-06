package gatekeeper.service.searchGuard

import com.floragunn.searchguard.tools.tlstool.SearchGuardTlsTool
import gatekeeper.log.LoggerDelegate
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.discovery.event.ServiceStartedEvent
import io.micronaut.runtime.event.annotation.EventListener
import io.micronaut.runtime.server.event.ServerStartupEvent
import io.micronaut.scheduling.annotation.Async
import javax.inject.Singleton

//@Singleton
open class SgCertificateService: ApplicationEventListener<ServerStartupEvent> {
    @Async
    override fun onApplicationEvent(event: ServerStartupEvent?) {
        generateCerts()
    }

    companion object {
        val logger by LoggerDelegate()
    }


    fun generateCerts(){
        logger.info("Generating search-guard certificates...")
        SystemExitControl.forbidSystemExitCall()
        SearchGuardTlsTool.main(arrayOf(
                "-c", "config/sg/sg_cert_config.yml",
                "-t", "config/sg/certs",
                "-ca", "-crt"
        ))
        SystemExitControl.enableSystemExitCall()
        logger.info("Finished certificate generation.")
    }


}