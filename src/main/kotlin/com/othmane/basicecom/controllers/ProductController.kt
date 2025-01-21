package com.othmane.basicecom.controllers

import com.othmane.basicecom.dtos.AddUpdateProductDTO
import com.othmane.basicecom.dtos.ProductDTO
import com.othmane.basicecom.services.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = ["*"])
class ProductController {

    @Autowired
    lateinit var productService: ProductService

    @GetMapping
    fun getProductsController() : ResponseEntity<List<ProductDTO>> {
        val products = productService.getProductsService()
        return ResponseEntity.ok(products)
    }

    @PostMapping
    fun addProductController(@RequestBody productToAdd: AddUpdateProductDTO) : ResponseEntity<ProductDTO> {
        val product = productService.addProductService(
            productToAdd.name,
            productToAdd.description,
            productToAdd.price,
            productToAdd.quantity)

        return ResponseEntity<ProductDTO>(product, HttpStatus.CREATED)
    }

    @PutMapping("/{productId}")
    fun updateProductController(@PathVariable productId: Long,
                                @RequestBody newProduct: AddUpdateProductDTO,
                                ): ResponseEntity<ProductDTO> {
        val updatedProduct = productService.updateProductService(
            productId,
            newProduct.name,
            newProduct.description,
            newProduct.price,
            newProduct.quantity)

        return ResponseEntity<ProductDTO>(updatedProduct, HttpStatus.OK)
    }

    @DeleteMapping("/{productId}")
    fun deleteProductController(@PathVariable productId: Long) : ResponseEntity<Unit> {
        productService.deleteProductService(productId)
        return ResponseEntity(HttpStatus.OK)
    }

}