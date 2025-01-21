package com.othmane.basicecom.services

import com.othmane.basicecom.dtos.OrderLineDTO
import com.othmane.basicecom.entities.Order
import com.othmane.basicecom.entities.OrderLine
import com.othmane.basicecom.entities.Product
import com.othmane.basicecom.enums.Status
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

    fun getOrdersService(userEmail: String) : List<Order> =
        when (userEmail) {
            "" -> orderRepository.findAll()
            else -> orderRepository.findAllByUserEmail(userEmail)

    }


    @Transactional
    fun addOrderService(orderLines : List<OrderLineDTO>, userEmail: String) : Order? {
        val user = userRepository.findByEmail(userEmail).orElseThrow{IllegalArgumentException("User not Found")}
        val order = Order(user = user)

        orderLines.forEach {
                val product : Product = productRepository.findById(it.productId).orElseThrow{IllegalArgumentException("Product not found")}
                if (product.productQuantity < it.orderLineQuantity) {
                    throw IllegalArgumentException("Product ${product.productName} is out of stock")
                }
                product.productQuantity -= it.orderLineQuantity
                productRepository.save(product)
                orderLineRepository.save(OrderLine(order = order, orderLineQuantity = it.orderLineQuantity, product = product))
        }

        return orderRepository.save(order)
    }

    @Transactional
    fun updateOrderStatusService(newStatus: String, userEmail: String, orderId: Long) : Order? {
        val order = orderRepository.findById(orderId).orElseThrow{IllegalArgumentException("Order not found")}
        order.orderStatus = Status.valueOf(newStatus)
        return orderRepository.save(order)
    }
}
