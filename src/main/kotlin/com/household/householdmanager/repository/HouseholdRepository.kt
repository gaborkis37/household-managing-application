package com.household.householdmanager.repository

import com.household.householdmanager.model.Household
import org.springframework.data.jpa.repository.JpaRepository

interface HouseholdRepository: JpaRepository<Household, Long> {
}