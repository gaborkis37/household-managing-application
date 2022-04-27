package com.household.householdmanager.service

import com.household.householdmanager.dto.HouseholdDTO
import com.household.householdmanager.exception.HouseholdNotFoundException
import com.household.householdmanager.model.Household
import com.household.householdmanager.repository.HouseholdRepository
import org.springframework.stereotype.Service

@Service
class HouseholdService(
    private val householdRepository: HouseholdRepository
) {

    fun save(householdDTO: HouseholdDTO): Household {
        val household = Household(
            name = householdDTO.name,
            image = householdDTO.image,
            products = householdDTO.products,
            type = householdDTO.type
        )

        return householdRepository.save(household)
    }

    fun findAll(): List<Household> {
        return householdRepository.findAll()
    }

    fun findById(id: Long): Household {
        return householdRepository.findById(id).orElseThrow { HouseholdNotFoundException("Household not found with the given id.") }
    }

    fun update(householdDTO: HouseholdDTO, id: Long): Household {
        return householdRepository.findById(id)
            .map { household ->
                val newHousehold = household.copy(
                    name = householdDTO.name,
                    type = householdDTO.type,
                    image = householdDTO.image,
                    products = householdDTO.products
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