package io.bootify.my_app.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToMany


@Entity
class Goods {

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
    var price: Int? = null

    @ManyToMany(mappedBy = "basketGoodss")
    var basketUsers: MutableSet<User>? = null

}
