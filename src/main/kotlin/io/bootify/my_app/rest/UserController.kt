package io.bootify.my_app.rest

import io.bootify.my_app.model.UserDTO
import io.bootify.my_app.service.UserService
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
    value = ["/api/users"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class UserController(
    private val userService: UserService
) {

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserDTO>> = ResponseEntity.ok(userService.findAll())

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<UserDTO> =
            ResponseEntity.ok(userService.get(id))

    @PostMapping
    fun createUser(@RequestBody @Valid userDTO: UserDTO): ResponseEntity<Long> =
            ResponseEntity(userService.create(userDTO), HttpStatus.CREATED)

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody @Valid userDTO: UserDTO):
            ResponseEntity<Void> {
        userService.update(id, userDTO)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
        userService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
