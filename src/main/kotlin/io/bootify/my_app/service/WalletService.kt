package io.bootify.my_app.service

import io.bootify.my_app.domain.Wallet
import io.bootify.my_app.model.WalletDTO
import io.bootify.my_app.repos.WalletRepository
import kotlin.streams.toList
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class WalletService(
    private val walletRepository: WalletRepository
) {

    fun findAll(): List<WalletDTO> {
        return walletRepository.findAll()
                .stream()
                .map { wallet -> mapToDTO(wallet, WalletDTO()) }
                .toList()
    }

    fun `get`(id: Long): WalletDTO {
        return walletRepository.findById(id)
                .map { wallet -> mapToDTO(wallet, WalletDTO()) }
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    fun create(walletDTO: WalletDTO): Long {
        val wallet = Wallet()
        mapToEntity(walletDTO, wallet)
        return walletRepository.save(wallet).id!!
    }

    fun update(id: Long, walletDTO: WalletDTO) {
        val wallet: Wallet = walletRepository.findById(id)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        mapToEntity(walletDTO, wallet)
        walletRepository.save(wallet)
    }

    fun delete(id: Long) {
        walletRepository.deleteById(id)
    }

    fun mapToDTO(wallet: Wallet, walletDTO: WalletDTO): WalletDTO {
        walletDTO.id = wallet.id
        walletDTO.name = wallet.name
        walletDTO.asset = wallet.asset
        return walletDTO
    }

    fun mapToEntity(walletDTO: WalletDTO, wallet: Wallet): Wallet {
        wallet.name = walletDTO.name
        wallet.asset = walletDTO.asset
        return wallet
    }

}
