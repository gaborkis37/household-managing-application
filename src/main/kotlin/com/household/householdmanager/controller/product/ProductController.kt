package com.household.householdmanager.controller.product

import com.household.householdmanager.dto.ProductDTO
import com.household.householdmanager.model.Product
import com.household.householdmanager.service.ProductService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/products")
class ProductController(
    private val productService: ProductService
) {

    @GetMapping("")
    fun findAll(): List<Product> {
        return productService.findAll()
    }

    @PostMapping("")
    fun save(@Valid @RequestBody productDTO: ProductDTO): Product {
        return productService.save(productDTO.toProduct())
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Product {
        return productService.findById(id)
    }

    @PutMapping("/{id}")
    fun update(@Valid @RequestBody productDTO: ProductDTO, @PathVariable id: Long): Product {
        return productService.update(productDTO.toProduct(), id)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): String {
        return productService.delete(id)
    }
}