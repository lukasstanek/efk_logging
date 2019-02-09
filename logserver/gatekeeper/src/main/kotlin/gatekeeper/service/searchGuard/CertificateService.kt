package gatekeeper.service.searchGuard

import com.floragunn.searchguard.tools.tlstool.SearchGuardTlsTool
import gatekeeper.log.LoggerDelegate
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.discovery.event.ServiceStartedEvent
import io.micronaut.runtime.event.annotation.EventListener
import io.micronaut.runtime.server.event.ServerStartupEvent
import io.micronaut.scheduling.annotation.Async
import javax.inject.Singleton

@Singleton
open class CertificateService {
    companion object {
        val logger by LoggerDelegate()
    }


    fun generateCerts(){
        logger.info("Generating search-guard certificates...")
        SystemExitControl.forbidSystemExitCall()
        try{
            SearchGuardTlsTool.main(arrayOf(
                    "-c", "config/sg/default/sg_cert_config.yml",
                    "-t", "config/sg/certs",
                    "-ca", "-crt"
            ))
        }catch(e: SystemExitControl.ExitTrappedException){

        }finally {
            SystemExitControl.enableSystemExitCall()
        }

        logger.info("Finished certificate generation.")
    }


}