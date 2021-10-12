package io.bootify.my_app.domain

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.OneToOne


@Entity
class User {

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
    var familyName: String? = null

    @Column
    var uID: Long? = null

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "basket",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "goods_id")]
    )
    var basketGoodss: MutableSet<Goods>? = null

    @OneToOne
    @JoinColumn(
        name = "walletr_id",
        nullable = false
    )
    var walletR: Wallet? = null

}
