package com.othmane.basicecom.entities

import com.fasterxml.jackson.annotation.JsonIncludeProperties
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.othmane.basicecom.enums.Status
import jakarta.persistence.*

@Entity
@Table(name = "Orders")
data class Order(
    @Enumerated(EnumType.STRING)
    var orderStatus: Status = Status.ORDERED,

    @Transient
    var totalPrice: Double? = null,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JsonIncludeProperties("id")
    var user : User,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    @Column(nullable = true)
    @JsonManagedReference
    var orderLines : MutableList<OrderLine>? = null

) : BaseEntity() {

        @PostLoad
        fun setTotalPrice() {
            var totalPrice = 0.0

            for (line in orderLines?: emptyList()) {
                totalPrice += line.orderLineQuantity * (line.product?.productPrice ?: 0.0)
            }
            this.totalPrice = totalPrice
        }
    }
