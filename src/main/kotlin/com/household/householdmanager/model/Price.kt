package com.household.householdmanager.model

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Price (
    @GeneratedValue
    @Id
    val id: Long,
    @Enumerated
    val currency: Currency,
    val amount: BigDecimal
)
