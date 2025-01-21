package com.othmane.basicecom.api.repositories

import com.othmane.basicecom.entities.Product
import com.othmane.basicecom.repositories.ProductRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProductsRepositoryTests {

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Test
    fun productRepository_Save_ReturnsSavedProduct() {
        val product = Product(
            productName = "Product",
            productDescription = "Description",
            productPrice = 100.0,
            productQuantity = 1,
        )

        val savedProduct = productRepository.save(product)

        Assertions.assertThat(savedProduct).isNotNull()
        Assertions.assertThat(savedProduct.id).isNotNull()
        Assertions.assertThat(savedProduct.productName).isEqualTo(product.productName)
        Assertions.assertThat(savedProduct.productDescription).isEqualTo(product.productDescription)
        Assertions.assertThat(savedProduct.productPrice).isEqualTo(product.productPrice)
        Assertions.assertThat(savedProduct.productQuantity).isEqualTo(product.productQuantity)


    }
}