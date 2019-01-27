package gatekeeper.controller

import gatekeeper.service.searchGuard.SgCertificateService
import gatekeeper.service.searchGuard.SgPermissionService
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import org.mindrot.jbcrypt.BCrypt
import org.mindrot.jbcrypt.BCrypt.hashpw
import javax.inject.Inject

@Controller("/sgadmin")
class SearchGuardController(@Inject val permissionSerivce: SgPermissionService,
                            @Inject val certificateService: SgCertificateService) {


    @Post("/update")
    fun updateElasticIndex(): MutableHttpResponse<Unit>? {
        permissionSerivce.updateESClusterPermissions()
        return HttpResponse.noContent<Unit>()
    }

    //Temporary function for testing
    @Get("/hash")
    fun hashPassword(password: String): String? {
        return hashpw(password, BCrypt.gensalt(12))
    }
}