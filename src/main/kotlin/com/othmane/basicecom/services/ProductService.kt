package com.othmane.basicecom.services

import com.othmane.basicecom.entities.Product
import com.othmane.basicecom.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService {

    @Autowired
    lateinit var productRepository: ProductRepository

    fun getProductsService() : List<Product> {
        return productRepository.findAll()
    }

    fun addProductService(productName: String,
                          productDescription: String,
                          productPrice: Double,
                          productQuantity: Int = 0,
                          ) : Product {
        val product = Product(
            productName,
            productDescription,
            productPrice,
            productQuantity,)

        return productRepository.save(product)
    }

    fun updateProductService(productId: Long,
                             productName: String,
                             productDescription: String,
                             productPrice: Double,
                             productQuantity: Int = 0,
                             ) : Product {

        val productToUpdate = productRepository.findById(productId)

        if (!productToUpdate.isPresent) {
            // TODO: Add Exception
            throw IllegalArgumentException("Product not found")
        }
        productToUpdate.get().productName = productName
        productToUpdate.get().productDescription = productDescription
        productToUpdate.get().productPrice = productPrice
        productToUpdate.get().productQuantity = productQuantity

        return productRepository.save(productToUpdate.get())
    }

    fun deleteProductService(productId: Long) {
        val productToDelete = productRepository.findById(productId)
        // TODO: Do it with .orElseThrow()

        if (!productToDelete.isPresent) {
            throw IllegalArgumentException("Product not found")
        }

        productRepository.deleteById(productId)
    }
}