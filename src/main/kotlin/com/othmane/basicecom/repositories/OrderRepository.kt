package com.othmane.basicecom.repositories

import com.othmane.basicecom.entities.Order
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : BaseRepository<Order> {

    fun findAllByUserEmail(userEmail: String): MutableList<Order>
}