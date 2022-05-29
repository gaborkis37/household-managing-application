package com.household.householdmanager.model

import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
@Data
@NoArgsConstructor
data class Role(
    @Id
    @GeneratedValue
    val id: Long? = null,
    @Enumerated(EnumType.STRING)
    val name: ERole
)