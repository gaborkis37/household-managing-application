package com.household.householdmanager.model

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class Household (
    @GeneratedValue
    @Id
    val id: Long? = null,
    val name: String,
    val type: String,
    @OneToMany(cascade = [CascadeType.ALL])
    val products: Set<Product>? = emptySet(),
    val image: String
)