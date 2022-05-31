package com.household.householdmanager.model

import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@Data
@NoArgsConstructor
data class Product(
    @Id
    @GeneratedValue
    val id: Long? = null,
    val name: String,
    val quantity: Int,
    val image: String,
    @Enumerated
    val type: ProductType
)