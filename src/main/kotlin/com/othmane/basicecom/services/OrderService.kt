package com.othmane.basicecom.services

import com.othmane.basicecom.dtos.OrderLineDTO
import com.othmane.basicecom.entities.Order
import com.othmane.basicecom.entities.OrderLine
import com.othmane.basicecom.repositories.OrderLineRepository
import com.othmane.basicecom.repositories.OrderRepository
import com.othmane.basicecom.repositories.ProductRepository
import com.othmane.basicecom.repositories.UserRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService {

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var orderRepository: OrderRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var orderLineRepository: OrderLineRepository

    fun getOrdersService() : List<Order> = orderRepository.findAll()

    @Transactional
    fun addOrderService(orderLines : List<OrderLineDTO>, userEmail: String) : Order? {
        val user = userRepository.findByEmail(userEmail)

        // TODO("Add builder")
        /* orderLines.map {
            orderLine -> {
                val product = productRepository.findById(orderLine.productId).orElseThrow()
                if (product.productQuantity < orderLine.orderLineQuantity) {
                    throw IllegalArgumentException("Product ${product.productName} is out of stock")
                }
                orderLineRepository.save(OrderLine())
            }
        } */
        return null
    }
}