package com.othmane.basicecom.dtos

data class AddUpdateProductDTO(
    val productName:String,
    val productDescription:String,
    val productPrice: Double,
    var productQuantity: Int = 0,
    )
