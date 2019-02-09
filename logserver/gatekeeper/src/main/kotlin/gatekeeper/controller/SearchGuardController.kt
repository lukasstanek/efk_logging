package gatekeeper.controller

import gatekeeper.service.searchGuard.CertificateService
import gatekeeper.service.searchGuard.SearchguardSyncService
import gatekeeper.util.PasswordHasher
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import javax.inject.Inject

@Controller("/sgadmin")
class SearchGuardController(@Inject val permissionSerivce: SearchguardSyncService,
                            @Inject val certificateService: CertificateService) {


    @Post("/update")
    fun updateElasticIndex(): MutableHttpResponse<Unit>? {
        permissionSerivce.updateESClusterPermissions()
        return HttpResponse.noContent<Unit>()
    }

    //Temporary function for testing
    @Get("/hash")
    fun hashPassword(password: String): String? {
        return PasswordHasher.hashPw(password)
    }
}