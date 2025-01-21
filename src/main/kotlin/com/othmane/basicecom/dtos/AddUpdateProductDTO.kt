package com.othmane.basicecom.dtos

data class AddUpdateProductDTO(
    val name:String,
    val description:String,
    val price: Double,
    var quantity: Int = 0
)
