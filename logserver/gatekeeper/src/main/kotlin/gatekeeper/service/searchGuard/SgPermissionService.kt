package gatekeeper.service.searchGuard

import com.floragunn.searchguard.tools.SearchGuardAdmin
import gatekeeper.log.LoggerDelegate
import io.micronaut.scheduling.annotation.Async
import javax.inject.Singleton

@Singleton
open class SgPermissionService{
    companion object {
        val logger by LoggerDelegate()
    }


    //plugins/search-guard-6/tools/sgadmin.sh \
    //-cd config/sg/ \
    //-ts config/sg/truststore.jks \
    //-ks config/sg/kirk-keystore.jks \
    //-nhnv \
    //-icl
    @Async
    open fun updateESClusterPermissions(){
        logger.info("Updating search-guard index")
        SystemExitControl.forbidSystemExitCall()
        SearchGuardAdmin.main(arrayOf(
                "-cd", "config/sg",
                "-cacert", "./config/sg/certs/root-ca.pem",
                "-cert", "./config/sg/certs/admin.pem",
                "-key", "./config/sg/certs/admin.key",
                "-nhnv", "-icl"))
        SystemExitControl.enableSystemExitCall()
        logger.info("Finished updating index")
    }


}