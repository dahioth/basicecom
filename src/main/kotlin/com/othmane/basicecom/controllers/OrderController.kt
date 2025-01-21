package com.othmane.basicecom.controllers

import com.othmane.basicecom.dtos.AddOrderDTO
import com.othmane.basicecom.dtos.PatchOrderStatusDTO
import com.othmane.basicecom.entities.Order
import com.othmane.basicecom.services.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/orders")
@CrossOrigin(origins = ["*"])
class OrderController {

    @Autowired
    lateinit var orderService: OrderService

    @GetMapping
    fun getOrdersController() : ResponseEntity<List<Order>> {
        val user = SecurityContextHolder.getContext().authentication
        val authorities = user.authorities.map { it.toString() }
        val role = authorities[0]
        if (role == "ROLE_ADMIN") {
            return ResponseEntity.ok(orderService.getOrdersService(""))
        }
        val userEmail = (user.principal as UserDetails).username
        return ResponseEntity.ok(orderService.getOrdersService(userEmail))
    }


    @PostMapping
    fun addOrderController(@RequestBody orderToAdd : AddOrderDTO) : ResponseEntity<Order> {
        val user = SecurityContextHolder.getContext().authentication.principal as UserDetails
        val userEmail = user.username
        val addedOrder = orderService.addOrderService(orderToAdd.orderLines, userEmail)
        return ResponseEntity(addedOrder, HttpStatus.CREATED)
    }

    @PatchMapping("/{orderId}/status")
    fun updateOrderStatusController(
        @PathVariable orderId: Long,
        @RequestBody patchStatusRequest: PatchOrderStatusDTO,
    ) : ResponseEntity<Order> {
        val user = SecurityContextHolder.getContext().authentication.principal as UserDetails
        val userEmail = user.username
        val updatedOrder = orderService.updateOrderStatusService(patchStatusRequest.status, userEmail, orderId)
        return ResponseEntity(updatedOrder, HttpStatus.OK)
    }


}