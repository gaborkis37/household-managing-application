package com.household.householdmanager.service

import com.household.householdmanager.dto.ProductDTO
import com.household.householdmanager.exception.ProductNotFoundException
import com.household.householdmanager.model.Product
import com.household.householdmanager.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {

    fun save(productDTO: ProductDTO): Product {
        val product = Product(
            name = productDTO.name,
            price = productDTO.price,
            image = productDTO.image,
            quantity = productDTO.quantity,
            type = productDTO.type
        )

        return productRepository.save(product)
    }

    fun findAll(): List<Product> {
        return productRepository.findAll()
    }

    fun findById(id: Long): Product {
        return productRepository.findById(id).orElseThrow { ProductNotFoundException("No product found with the given id.") }
    }

    fun update(productDTO: ProductDTO, id: Long): Product {
        return productRepository.findById(id).map { product ->
            val updatedProduct = product.copy(
                name = productDTO.name,
                quantity = productDTO.quantity,
                price = productDTO.price,
                image = productDTO.image,
                type = productDTO.type
            )

            productRepository.save(updatedProduct)
        }.orElseThrow { ProductNotFoundException("No product found with the given id.") }
    }

    fun delete(id: Long): String {
        try {
            productRepository.deleteById(id)
            return "Product deleted successfully"
        } catch (e: Exception) {
            throw ProductNotFoundException("No product found with the given id.")
        }
    }
}