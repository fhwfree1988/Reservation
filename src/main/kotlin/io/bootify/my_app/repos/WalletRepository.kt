package io.bootify.my_app.repos

import io.bootify.my_app.domain.Wallet
import org.springframework.data.jpa.repository.JpaRepository


interface WalletRepository : JpaRepository<Wallet, Long>
