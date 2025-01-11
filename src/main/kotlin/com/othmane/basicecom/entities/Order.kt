package com.othmane.basicecom.entities

import com.othmane.basicecom.enums.Status
import jakarta.persistence.*

@Entity
data class Order(
    @Enumerated(EnumType.STRING)
    var orderStatus: Status = Status.IN_PROGRESS,

    @Transient
    var totalPrice: Double,

    @OneToMany()
    @JoinColumn(name = "order_id")
    var orderLines : MutableList<OrderLine>,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user : User,

    ) : BaseEntity()
