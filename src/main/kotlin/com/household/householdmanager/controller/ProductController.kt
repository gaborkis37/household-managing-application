package com.household.householdmanager.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController {

    @GetMapping("/")
    fun test(): String {
        return "App is running"
    }
}