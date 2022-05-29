package com.household.householdmanager.service

import com.household.householdmanager.model.UserDetailsImpl
import com.household.householdmanager.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return UserDetailsImpl(username?.let { userRepository.findOneByEmail(it) }!!)
    }
}