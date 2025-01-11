package com.othmane.basicecom.controllers

import com.othmane.basicecom.dtos.AddUpdateProductDTO
import com.othmane.basicecom.entities.Product
import com.othmane.basicecom.services.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController {

    @Autowired
    lateinit var productService: ProductService

    @GetMapping
    fun getProductsController() : ResponseEntity<List<Product>> {
        val products = productService.getProductsService()
        return ResponseEntity.ok(products)
    }

    @PostMapping
    fun addProductController(@RequestBody productToAdd: AddUpdateProductDTO) : ResponseEntity<Product> {
        val product = productService.addProductService(
            productToAdd.productName,
            productToAdd.productDescription,
            productToAdd.productPrice,
            productToAdd.productQuantity,
            )
        return ResponseEntity<Product>(product, HttpStatus.CREATED)
    }

    @PutMapping("/{productId}")
    fun updateProductController(@PathVariable productId: Long,
                                @RequestBody newProduct: AddUpdateProductDTO,
                                ): ResponseEntity<Product> {
        val updatedProduct = productService.updateProductService(
            productId,
            newProduct.productName,
            newProduct.productDescription,
            newProduct.productPrice,
            newProduct.productQuantity,
        )

        return ResponseEntity<Product>(updatedProduct, HttpStatus.OK)
    }

    @DeleteMapping("/{productId}")
    fun deleteProductController(@PathVariable productId: Long,) : ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.OK)
    }

}