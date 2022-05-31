package com.household.householdmanager.service

import com.household.householdmanager.dto.HouseholdDTO
import com.household.householdmanager.dto.ProductDTO
import com.household.householdmanager.exception.HouseholdNotFoundException
import com.household.householdmanager.mapper.ProductDTOToProductMapper
import com.household.householdmanager.model.Household
import com.household.householdmanager.model.Product
import com.household.householdmanager.repository.HouseholdRepository
import org.springframework.stereotype.Service

@Service
class HouseholdService(
    private val householdRepository: HouseholdRepository
) {

    fun save(household: Household): Household {
        return householdRepository.save(household)
    }

    fun addProductToHousehold(product: Product, householdId: Long): Household {
        return householdRepository
            .findById(householdId)
            .map { household ->
                val updatedProducts = household.products?.toMutableSet()?.plus(product)
                val updatedHousehold = household.copy(
                    products = updatedProducts
                )

                householdRepository.save(updatedHousehold)
            }
            .orElseThrow { HouseholdNotFoundException("Household not found with the given id.") }
    }

    fun findAll(): List<Household> {
        return householdRepository.findAll()
    }

    fun findById(id: Long): Household {
        return householdRepository.findById(id).orElseThrow { HouseholdNotFoundException("Household not found with the given id.") }
    }

    fun update(household: Household, id: Long): Household {
        return householdRepository.findById(id)
            .map { household ->
                val newHousehold = household.copy(
                    name = household.name,
                    type = household.type,
                    image = household.image,
                    products = household.products
                )

                householdRepository.save(newHousehold)
            }.orElseThrow { HouseholdNotFoundException("Household not found with the given id.") }
    }

    fun delete(id: Long): String {
        try {
            householdRepository.deleteById(id)
            return "Household deleted successfully"
        } catch (e: Exception) {
            throw HouseholdNotFoundException("Household not found with the given id.")
        }
    }
}