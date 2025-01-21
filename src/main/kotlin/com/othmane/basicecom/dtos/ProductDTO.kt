package com.othmane.basicecom.dtos

data class ProductDTO(
    var id: Long?,
    var name: String,
    var description: String,
    var price: Double,
    var quantity: Int,
)
