package io.bootify.my_app.service

import io.bootify.my_app.domain.Goods
import io.bootify.my_app.model.GoodsDTO
import io.bootify.my_app.repos.GoodsRepository
import kotlin.streams.toList
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class GoodsService(
    private val goodsRepository: GoodsRepository
) {

    fun findAll(): List<GoodsDTO> {
        return goodsRepository.findAll()
                .stream()
                .map { goods -> mapToDTO(goods, GoodsDTO()) }
                .toList()
    }

    fun `get`(id: Long): GoodsDTO {
        return goodsRepository.findById(id)
                .map { goods -> mapToDTO(goods, GoodsDTO()) }
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    fun create(goodsDTO: GoodsDTO): Long {
        val goods = Goods()
        mapToEntity(goodsDTO, goods)
        return goodsRepository.save(goods).id!!
    }

    fun update(id: Long, goodsDTO: GoodsDTO) {
        val goods: Goods = goodsRepository.findById(id)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        mapToEntity(goodsDTO, goods)
        goodsRepository.save(goods)
    }

    fun delete(id: Long) {
        goodsRepository.deleteById(id)
    }

    fun mapToDTO(goods: Goods, goodsDTO: GoodsDTO): GoodsDTO {
        goodsDTO.id = goods.id
        goodsDTO.name = goods.name
        goodsDTO.price = goods.price
        return goodsDTO
    }

    fun mapToEntity(goodsDTO: GoodsDTO, goods: Goods): Goods {
        goods.name = goodsDTO.name
        goods.price = goodsDTO.price
        return goods
    }

}
