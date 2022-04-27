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
    var name: String,
    val quantity: Int,
    val image: String,
    val type: String,
    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name="price_id")
    val price: Price
)