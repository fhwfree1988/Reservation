package io.bootify.my_app.service

import io.bootify.my_app.domain.Goods
import io.bootify.my_app.domain.User
import io.bootify.my_app.domain.Wallet
import io.bootify.my_app.model.UserDTO
import io.bootify.my_app.repos.GoodsRepository
import io.bootify.my_app.repos.UserRepository
import io.bootify.my_app.repos.WalletRepository
import javax.transaction.Transactional
import kotlin.streams.toList
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Transactional
@Service
class UserService(
    private val userRepository: UserRepository,
    private val goodsRepository: GoodsRepository,
    private val walletRepository: WalletRepository
) {

    fun findAll(): List<UserDTO> {
        return userRepository.findAll()
                .stream()
                .map { user -> mapToDTO(user, UserDTO()) }
                .toList()
    }

    fun `get`(id: Long): UserDTO {
        return userRepository.findById(id)
                .map { user -> mapToDTO(user, UserDTO()) }
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    fun create(userDTO: UserDTO): Long {
        val user = User()
        mapToEntity(userDTO, user)
        return userRepository.save(user).id!!
    }

    fun update(id: Long, userDTO: UserDTO) {
        val user: User = userRepository.findById(id)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        mapToEntity(userDTO, user)
        userRepository.save(user)
    }

    fun delete(id: Long) {
        userRepository.deleteById(id)
    }

    fun mapToDTO(user: User, userDTO: UserDTO): UserDTO {
        userDTO.id = user.id
        userDTO.name = user.name
        userDTO.familyName = user.familyName
        userDTO.uID = user.uID
        userDTO.baskets = user.basketGoodss?.stream()
                ?.map { goods -> goods.id!! }
                ?.toList()
        userDTO.walletR = user.walletR?.id
        return userDTO
    }

    fun mapToEntity(userDTO: UserDTO, user: User): User {
        user.name = userDTO.name
        user.familyName = userDTO.familyName
        user.uID = userDTO.uID
        if (userDTO.baskets != null) {
            val baskets: List<Goods> = goodsRepository.findAllById(userDTO.baskets!!)
            if (baskets.size != userDTO.baskets!!.size) {
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "one of baskets not found")
            }
            user.basketGoodss = baskets.toMutableSet()
        }
        if (userDTO.walletR != null && (user.walletR == null || user.walletR?.id !=
                userDTO.walletR)) {
            val walletR: Wallet = walletRepository.findById(userDTO.walletR!!)
                    .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND,
                            "walletR not found") }
            user.walletR = walletR
        }
        return user
    }

}
