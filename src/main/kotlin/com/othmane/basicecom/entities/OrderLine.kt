package com.othmane.basicecom.entities

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne

@Entity
data class OrderLine(
    @ManyToOne
    @JoinColumn(name = "order_id")
    val order: Order,

    @OneToOne
    @JoinColumn(name = "product_id")
    val product: Product,

    var orderLineQuantity: Int,

    ) : BaseEntity()
