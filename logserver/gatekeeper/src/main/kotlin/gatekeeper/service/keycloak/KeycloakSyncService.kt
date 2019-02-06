package gatekeeper.service.keycloak

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.rx.rxObject
import com.github.kittinunf.fuel.rx.rxResponseString
import io.micronaut.http.client.RxHttpClient
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder
import org.jboss.resteasy.spi.ResteasyConfiguration
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KeycloakSyncService(
//        @Inject val http: RxHttpClient
) {
    companion object {
        val BASE_URL = "http://localhost:8080/auth"
        val GROUPS = "/admin/realms/master/groups"
    }

    private val keycloakClient: Keycloak

    init {
        keycloakClient = KeycloakBuilder.builder()
                .serverUrl(BASE_URL)
                .realm("master")
                .clientId("admin-cli")
                .username("admin")
                .password("admin")
                .build()
    }


    fun retrieveGroups(){
        val groups = keycloakClient.realm("master").groups()
        println(groups.groups())
    }
}