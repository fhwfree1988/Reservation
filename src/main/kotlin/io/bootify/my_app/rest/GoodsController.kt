package io.bootify.my_app.rest

import io.bootify.my_app.model.GoodsDTO
import io.bootify.my_app.service.GoodsService
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
    value = ["/api/goodss"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class GoodsController(
    private val goodsService: GoodsService
) {

    @GetMapping
    fun getAllGoodss(): ResponseEntity<List<GoodsDTO>> = ResponseEntity.ok(goodsService.findAll())

    @GetMapping("/{id}")
    fun getGoods(@PathVariable id: Long): ResponseEntity<GoodsDTO> =
            ResponseEntity.ok(goodsService.get(id))

    @PostMapping
    fun createGoods(@RequestBody @Valid goodsDTO: GoodsDTO): ResponseEntity<Long> =
            ResponseEntity(goodsService.create(goodsDTO), HttpStatus.CREATED)

    @PutMapping("/{id}")
    fun updateGoods(@PathVariable id: Long, @RequestBody @Valid goodsDTO: GoodsDTO):
            ResponseEntity<Void> {
        goodsService.update(id, goodsDTO)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    fun deleteGoods(@PathVariable id: Long): ResponseEntity<Void> {
        goodsService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
