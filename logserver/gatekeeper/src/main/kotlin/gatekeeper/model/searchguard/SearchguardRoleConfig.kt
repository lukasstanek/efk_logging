package gatekeeper.model.searchguard

data class SearchguardRoleConfig(
        val readonly: Boolean,
        val cluster: List<String>,
        val indices: Map<String, Map<String, List<String>>>
)

fun generateLoggroupConfig(roleName: String): SearchguardRoleConfig {
    return SearchguardRoleConfig(
            false,
            listOf("CLUSTER_MONITOR",
                    "CLUSTER_COMPOSITE_OPS",
                    "indices:admin/template/get",
                    "indices:admin/template/put"),
            mapOf(
                Pair("log-$roleName-*", mapOf(
                        Pair("*", listOf(
                            "CRUD",
                            "CREATE_INDEX"
                        ))
                )),
                Pair("*beat*", mapOf(
                        Pair("*", listOf(
                                "CRUD",
                                "CREATE_INDEX"
                        )))
                )))
}