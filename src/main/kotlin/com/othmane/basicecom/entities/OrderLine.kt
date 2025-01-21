package com.othmane.basicecom.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIncludeProperties
import jakarta.persistence.*

@Entity
data class OrderLine(
    var orderLineQuantity: Int,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JsonIncludeProperties("id")
    var product: Product,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JsonBackReference
    var order: Order
) : BaseEntity()
