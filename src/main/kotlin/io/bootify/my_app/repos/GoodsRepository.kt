package io.bootify.my_app.repos

import io.bootify.my_app.domain.Goods
import org.springframework.data.jpa.repository.JpaRepository


interface GoodsRepository : JpaRepository<Goods, Long>
