package com.household.householdmanager.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors

open class UserDetailsImpl(user: User) : User(user), UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return roles.stream()
            .map { role -> SimpleGrantedAuthority(role.name.toString()) }
            .collect(Collectors.toList())
    }

    override fun getPassword(): String {
        return super.passWord
    }

    override fun getUsername(): String {
        return "${super.firstName} ${super.lastName}"
    }

    override fun isAccountNonExpired(): Boolean {
        return super.accountNonExpired
    }

    override fun isAccountNonLocked(): Boolean {
        return super.accountNonLocked
    }

    override fun isCredentialsNonExpired(): Boolean {
        return super.credentialsNonExpired
    }

    override fun isEnabled(): Boolean {
        return super.enabled
    }
}