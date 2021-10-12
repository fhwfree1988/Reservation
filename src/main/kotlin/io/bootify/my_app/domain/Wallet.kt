package io.bootify.my_app.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne


@Entity
class Wallet {

    @Id
    @Column(
        nullable = false,
        updatable = false
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column
    var name: String? = null

    @Column
    var asset: Int? = null

    @OneToOne(
        mappedBy = "walletR",
        fetch = FetchType.LAZY,
        optional = false
    )
    var walletR: User? = null

}
