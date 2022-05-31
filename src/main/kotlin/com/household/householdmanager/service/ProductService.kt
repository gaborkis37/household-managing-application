package com.household.householdmanager.service

import com.household.householdmanager.exception.ProductNotFoundException
import com.household.householdmanager.model.Product
import com.household.householdmanager.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {

    fun save(product: Product): Product {
        return productRepository.save(product)
    }

    fun findAll(): List<Product> {
        return productRepository.findAll()
    }

    fun findById(id: Long): Product {
        return productRepository.findById(id).orElseThrow { ProductNotFoundException("No product found with the given id.") }
    }

    fun update(product: Product, id: Long): Product {
        return productRepository.findById(id).map { currentProduct ->
            val updatedProduct = currentProduct.copy(
                name = product.name,
                quantity = product.quantity,
                image = product.image,
                type = product.type
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