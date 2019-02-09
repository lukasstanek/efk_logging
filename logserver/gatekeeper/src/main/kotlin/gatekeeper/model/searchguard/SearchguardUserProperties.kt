package gatekeeper.model.searchguard

data class SearchguardUserProperties(
        val readonly: Boolean,
        val hash: String,
        val roles: List<String>
)