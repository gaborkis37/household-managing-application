package com.household.householdmanager.dto

import com.household.householdmanager.model.Product

data class HouseholdDTO(
    val name: String,
    val type: String,
    val products: Set<Product>?,
    val image: String
)