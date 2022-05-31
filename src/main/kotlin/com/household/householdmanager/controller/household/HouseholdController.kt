package com.household.householdmanager.controller.household

import com.household.householdmanager.dto.HouseholdDTO
import com.household.householdmanager.dto.ProductDTO
import com.household.householdmanager.model.Household
import com.household.householdmanager.service.HouseholdService
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
@RequestMapping("/api/v1/households")
class HouseholdController(
    private val householdService: HouseholdService
) {

    @GetMapping("")
    fun findAll(): List<Household> {
        return householdService.findAll()
    }

    @PostMapping("")
    fun save(@RequestBody @Valid householdDTO: HouseholdDTO): Household {
        return householdService.save(householdDTO.toHousehold())
    }

    @PostMapping("/{id}/product")
    fun addProductToHousehold(@RequestBody @Valid productDTO: ProductDTO, @PathVariable id: Long): Household {
        return householdService.addProductToHousehold(productDTO.toProduct(), id)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Household {
        return householdService.findById(id)
    }

    @PutMapping("/{id}")
    fun update(@RequestBody @Valid householdDTO: HouseholdDTO, @PathVariable id: Long): Household {
        return householdService.update(householdDTO.toHousehold(), id)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): String {
        return householdService.delete(id)
    }
}