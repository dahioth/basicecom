package com.othmane.basicecom.entities

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne

@Entity
data class Product(
    var productName: String,
    var productDescription: String,
    var productPrice: Double,
    var productQuantity: Int = 0,

    @OneToOne
    @JoinColumn(name = "product_id")
    var orderLine: OrderLine? = null,
    ) : BaseEntity()
