package gatekeeper.service.keycloak

import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.keycloak.representations.idm.GroupRepresentation
import org.keycloak.representations.idm.UserRepresentation
import javax.inject.Singleton

@Singleton
class KeycloakApi {
    companion object {
        const val BASE_URL = "http://localhost:8080/auth"
        const val REALM = "master"
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


    fun retrieveGroups(): List<GroupRepresentation> {
        return keycloakClient.realm(REALM).groups().groups()
    }

    fun retrieveUsers(): List<UserRepresentation> {
        return keycloakClient.realm(REALM).users().list()
    }
}