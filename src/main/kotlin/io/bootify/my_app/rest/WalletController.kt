package io.bootify.my_app.rest

import io.bootify.my_app.model.WalletDTO
import io.bootify.my_app.service.WalletService
import java.lang.Void
import javax.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(
    value = ["/api/wallets"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class WalletController(
    private val walletService: WalletService
) {

    @GetMapping
    fun getAllWallets(): ResponseEntity<List<WalletDTO>> =
            ResponseEntity.ok(walletService.findAll())

    @GetMapping("/{id}")
    fun getWallet(@PathVariable id: Long): ResponseEntity<WalletDTO> =
            ResponseEntity.ok(walletService.get(id))

    @PostMapping
    fun createWallet(@RequestBody @Valid walletDTO: WalletDTO): ResponseEntity<Long> =
            ResponseEntity(walletService.create(walletDTO), HttpStatus.CREATED)

    @PutMapping("/{id}")
    fun updateWallet(@PathVariable id: Long, @RequestBody @Valid walletDTO: WalletDTO):
            ResponseEntity<Void> {
        walletService.update(id, walletDTO)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    fun deleteWallet(@PathVariable id: Long): ResponseEntity<Void> {
        walletService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
