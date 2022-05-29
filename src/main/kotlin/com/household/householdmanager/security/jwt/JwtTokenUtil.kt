package com.household.householdmanager.security.jwt

import com.household.householdmanager.model.UserDetailsImpl
import com.household.householdmanager.security.config.JwtConfig
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.SignatureException
import io.jsonwebtoken.UnsupportedJwtException
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenUtil(
    private val jwtConfig: JwtConfig
) {
    private val logger = LoggerFactory.getLogger(JwtTokenUtil::class.java)

    fun generateJwtToken(authentication: Authentication): String {
        val userDetailsImpl: UserDetailsImpl = authentication.principal as UserDetailsImpl

        return Jwts.builder()
            .setSubject(userDetailsImpl.email)
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + jwtConfig.expirationMs))
            .signWith(SignatureAlgorithm.HS256, jwtConfig.secret)
            .compact()
    }

    fun getEmailFromJwtToken(token: String): String {
        return Jwts.parser().setSigningKey(jwtConfig.secret).parseClaimsJws(token).body.subject
    }

    fun validateJwtToken(token: String): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtConfig.secret).parseClaimsJws(token)
            return true
        } catch (e: SignatureException) {
            logger.error("Invalid JWT signature: ${e.message}")
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token: ${e.message}")
        } catch (e: ExpiredJwtException) {
            logger.error("Jwt token expired: ${e.message}")
        } catch (e: UnsupportedJwtException) {
            logger.error("JWT token unsupported: ${e.message}")
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty: ${e.message}")
        }

        return false
    }
}
