package io.hfx.pluginx.platform.model

data class ResolvedRepository(
    val name: String,
    val url: String,
    val user: String? = null,
    val password: String? = null,
    val isAllowInsecureProtocol: Boolean = false
)