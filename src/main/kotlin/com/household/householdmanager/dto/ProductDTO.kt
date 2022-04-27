package com.household.householdmanager.dto

import com.household.householdmanager.model.Price

data class ProductDTO(
    val name: String,
    val quantity: Int,
    val image: String,
    val type: String,
    val price: Price
)
