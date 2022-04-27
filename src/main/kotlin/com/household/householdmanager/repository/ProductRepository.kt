package com.household.householdmanager.repository

import com.household.householdmanager.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long> {
}