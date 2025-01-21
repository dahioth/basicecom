package com.othmane.basicecom.seeders

import com.othmane.basicecom.entities.Product
import com.othmane.basicecom.repositories.OrderLineRepository
import com.othmane.basicecom.repositories.OrderRepository
import com.othmane.basicecom.repositories.ProductRepository
import com.othmane.basicecom.repositories.UserRepository
import com.othmane.basicecom.entities.User
import com.othmane.basicecom.enums.Role
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
class SeederConfig : CommandLineRunner {

    @Autowired
    private lateinit var orderLineRepository: OrderLineRepository

    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var encoder: PasswordEncoder

    override fun run(vararg args: String) {
        try {
            val users = listOf(
                User(
                    "Othmane",
                    "Dahi",
                    "othmane@gmail.com",
                    encoder.encode("othmane"),
                    Role.ADMIN
                ),
                User(
                    "Admin",
                    "Admin",
                    "admin@gmail.com",
                    encoder.encode("admin"),
                    Role.ADMIN
                ),
                User(
                    "John",
                    "Doe",
                    "john@gmail.com",
                    encoder.encode("john"),
                    Role.USER
                )
            )

            val products = listOf(
                Product(
                    "Headphones",
                    "High Quality",
                    99.9,
                    100
                ),
                Product(
                    "Air Frier",
                    "High Quality",
                    24.9,
                    5
                )
            )
            cleanDB()
            users.forEach {
                userRepository.save(it)
                println("Saving User; $it")
            }
            println("Users Saved!")
            products.forEach {
                productRepository.save(it)
                println("Saving Product; $it")
            }
            println("Products Saved!")
        } catch (e: Exception) {
            println("Unable to seed DB: " + e.message)
        }
    }

    fun cleanDB() {
        orderLineRepository.deleteAll()
        productRepository.deleteAll()
        orderRepository.deleteAll()
        userRepository.deleteAll()
    }

}