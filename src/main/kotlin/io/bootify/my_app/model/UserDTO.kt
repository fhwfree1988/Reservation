package io.bootify.my_app.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


data class UserDTO(
    var id: Long? = null,
    @get:Size(max = 255)
    var name: String? = null,
    @get:Size(max = 255)
    var familyName: String? = null,
    @get:JsonProperty("uID")
    var uID: Long? = null,
    var baskets: List<Long>? = null,
    @get:NotNull
    var walletR: Long? = null
)
