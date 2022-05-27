package com.household.householdmanager.dto

import com.household.householdmanager.model.ProductType

data class ProductDTO(
    val name: String,
    val quantity: Int,
    val image: String,
    val type: ProductType,
)
