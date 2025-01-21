package com.othmane.basicecom.services

import com.othmane.basicecom.dtos.ProductDTO
import com.othmane.basicecom.entities.Product
import com.othmane.basicecom.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService {

    @Autowired
    lateinit var productRepository: ProductRepository

    fun getProductsService() : List<ProductDTO> {
        return productRepository.findAll().map {
            it.mapToProduct()
        }
    }

    fun addProductService(productName: String,
                          productDescription: String,
                          productPrice: Double,
                          productQuantity: Int = 0,
                          ) : ProductDTO {
        val product = Product(
            productName,
            productDescription,
            productPrice,
            productQuantity,)

        return productRepository.save(product).mapToProduct()
    }

    fun updateProductService(productId: Long,
                             productName: String,
                             productDescription: String,
                             productPrice: Double,
                             productQuantity: Int,
                             ) : ProductDTO {

        val productToUpdate = productRepository.findById(productId).orElseThrow{
            throw IllegalArgumentException("Product not found")
        }

        productToUpdate.productName = productName
        productToUpdate.productDescription = productDescription
        productToUpdate.productPrice = productPrice
        productToUpdate.productQuantity = productQuantity

        return productRepository.save(productToUpdate).mapToProduct()
    }

    fun deleteProductService(productId: Long) {
        val productToDelete = productRepository.findById(productId)

        if (!productToDelete.isPresent) {
            throw IllegalArgumentException("Product not found")
        }

        productRepository.deleteById(productId)
    }

    private fun Product.mapToProduct(): ProductDTO =
        ProductDTO(
            id = id,
            name = productName,
            description = productDescription,
            price = productPrice,
            quantity = productQuantity,
        )
}