package com.household.householdmanager.model

import javax.persistence.*

@Entity
open class User(
    @Id
    @GeneratedValue
    var id: Long? = null,
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var passWord: String = "",
    var image: String = "",
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles: Set<Role> = HashSet()
) {
    var accountNonExpired: Boolean = true
    var accountNonLocked: Boolean = true
    var credentialsNonExpired: Boolean = true
    var enabled: Boolean = true

    constructor(user: User): this(
        null,
        user.firstName,
        user.lastName,
        user.email,
        user.passWord,
        user.image,
        user.roles
    ) {
        id = user.id
        firstName = user.firstName
        lastName = user.lastName
        email = user.email
        passWord = user.passWord
        image = user.image
        accountNonExpired = user.accountNonExpired
        accountNonLocked = user.accountNonLocked
        credentialsNonExpired = user.credentialsNonExpired
        enabled = user.enabled
        roles = user.roles
    }
}