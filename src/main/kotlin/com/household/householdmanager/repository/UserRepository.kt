package com.household.householdmanager.repository

import com.household.householdmanager.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository: JpaRepository<User, Long> {
    fun findOneByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
}