package com.household.householdmanager.security.jwt

import com.household.householdmanager.service.UserDetailsServiceImpl
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthTokenFilter(
    private val jwtUtils: JwtTokenUtil,
    private val userDetailsServiceImpl: UserDetailsServiceImpl
): OncePerRequestFilter() {
    private val log = LoggerFactory.getLogger(AuthTokenFilter::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val jwt = parseJwt(request)
            if(jwt != null && jwtUtils.validateJwtToken(jwt)) {
                val email = jwtUtils.getEmailFromJwtToken(jwt)
                val userDetails = userDetailsServiceImpl.loadUserByUsername(email)
                val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (e: Exception) {
            log.error("Cannot set user authentication: $e")
        }

        filterChain.doFilter(request, response)
    }

    private fun parseJwt(request: HttpServletRequest): String? {
        val headerAuth = request.getHeader("Authorization")
        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            val token = headerAuth.substring(7, headerAuth.length)
            log.info(token)
            return token
        }
        return null
    }
}