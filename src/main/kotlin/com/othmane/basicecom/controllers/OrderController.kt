package com.othmane.basicecom.controllers

import com.othmane.basicecom.dtos.AddUpdateOrderDTO
import com.othmane.basicecom.entities.Order
import com.othmane.basicecom.services.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderController {

    @Autowired
    lateinit var orderService: OrderService

    @GetMapping
    fun getOrdersController() : ResponseEntity<List<Order>> =
        ResponseEntity.ok(orderService.getOrdersService())

    @PostMapping
    fun addOrderController(@RequestBody orderToAdd : AddUpdateOrderDTO) : ResponseEntity<Order> {
        // TODO: retrieve user
        val userEmail = "othmane@gmail.com"
        val addedOrder = orderService.addOrderService(orderToAdd.orderLines, userEmail)
        return ResponseEntity(addedOrder, HttpStatus.CREATED)
    }


}