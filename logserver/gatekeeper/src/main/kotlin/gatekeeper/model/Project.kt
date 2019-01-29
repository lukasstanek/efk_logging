package gatekeeper.model

data class Project(
        val name: String,
        val indexPrefix: String,
        val passwordHash: String
)