package io.bootify.my_app.model

import javax.validation.constraints.Size


data class WalletDTO(
    var id: Long? = null,
    @get:Size(max = 255)
    var name: String? = null,
    var asset: Int? = null
)
