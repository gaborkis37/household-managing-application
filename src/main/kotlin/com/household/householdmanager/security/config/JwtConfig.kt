package com.household.householdmanager.security.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "jwt")
data class JwtConfig(
    var secret: String = "",
    var expirationMs: Int = 0
)