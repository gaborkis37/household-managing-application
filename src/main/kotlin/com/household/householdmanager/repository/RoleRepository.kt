package com.household.householdmanager.repository

import com.household.householdmanager.model.ERole
import com.household.householdmanager.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface RoleRepository: JpaRepository<Role, Long> {
    fun findByName(name: ERole): Role
}