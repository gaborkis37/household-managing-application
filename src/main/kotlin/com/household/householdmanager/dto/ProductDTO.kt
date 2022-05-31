package com.household.householdmanager.dto

import com.household.householdmanager.model.Product
import com.household.householdmanager.model.ProductType
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

data class ProductDTO(
    @NotEmpty(message = "Product name cannot be empty.")
    @Size(min = 2, max = 70, message = "Name length should be between 2 and 50 characters.")
    val name: String,
    @Positive
    @NotNull
    val quantity: Int,
    val image: String,
    @NotNull
    val type: ProductType,
) {

    fun toProduct(): Product {
        return Product(
            name = name,
            quantity = quantity,
            image = image,
            type = type
        )
    }
}
