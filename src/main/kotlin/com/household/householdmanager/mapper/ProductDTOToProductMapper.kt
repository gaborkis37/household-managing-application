package com.household.householdmanager.mapper

import com.household.householdmanager.dto.ProductDTO
import com.household.householdmanager.model.Product

class ProductDTOToProductMapper {
    companion object {
        fun map(productDTO: ProductDTO): Product {
            return Product(
                name = productDTO.name,
                type = productDTO.type,
                quantity = productDTO.quantity,
                image = productDTO.image,
            )
        }
    }
}