package com.household.householdmanager.controller.household

import com.household.householdmanager.dto.HouseholdDTO
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
    fun save(@RequestBody householdDTO: HouseholdDTO): Household {
        return householdService.save(householdDTO)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Household {
        return householdService.findById(id)
    }

    @PutMapping("/{id}")
    fun update(@RequestBody householdDTO: HouseholdDTO, @PathVariable id: Long): Household {
        return householdService.update(householdDTO, id)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): String {
        return householdService.delete(id)
    }
}