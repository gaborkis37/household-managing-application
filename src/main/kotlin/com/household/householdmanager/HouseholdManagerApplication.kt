package com.household.householdmanager

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource

@SpringBootApplication
@EnableAutoConfiguration
@PropertySource("application.properties")
class HouseholdManagerApplication

fun main(args: Array<String>) {
	runApplication<HouseholdManagerApplication>(*args)
}
