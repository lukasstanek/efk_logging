package gatekeeper.service.searchGuard

import com.floragunn.searchguard.tools.SearchGuardAdmin
import gatekeeper.constants.Constants.CERTS_PATH
import gatekeeper.constants.Constants.CURRENT_CONFIG_PATH
import gatekeeper.constants.Constants.DEFAULT_CONFIG_PATH
import gatekeeper.log.LoggerDelegate
import io.micronaut.scheduling.annotation.Async
import javax.inject.Singleton

@Singleton
open class SearchguardSyncService{
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
    open fun updateESClusterPermissions(configPath: String = CURRENT_CONFIG_PATH){
        logger.info("Updating search-guard index")
        SystemExitControl.forbidSystemExitCall()
        try{
            SearchGuardAdmin.main(arrayOf(
                    "-cd", configPath,
                    "-cacert", "$CERTS_PATH/root-ca.pem",
                    "-cert", "$CERTS_PATH/admin.pem",
                    "-key", "$CERTS_PATH/admin.key",
                    "-nhnv", "-icl"))
        }catch (e: SystemExitControl.ExitTrappedException){

        }finally {
            SystemExitControl.enableSystemExitCall()
        }

        logger.info("Finished updating index")
    }


}