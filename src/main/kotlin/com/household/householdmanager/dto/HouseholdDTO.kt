package com.household.householdmanager.dto

import com.household.householdmanager.model.Household
import com.household.householdmanager.model.Product
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class HouseholdDTO(
    @NotEmpty(message = "Household name cannot be empty.")
    @Size(min = 2, max = 70, message = "Household name must be between 2 and 70 characters.")
    val name: String,
    @NotEmpty(message = "Type cannot be null.")
    val type: String,
    val products: Set<Product>?,
    val image: String
) {
    fun toHousehold(): Household {
        return Household(
            name = name,
            type = type,
            products = products,
            image = image
        )
    }
}