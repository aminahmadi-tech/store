package ir.aminahmadi24.store.controller;

import ir.aminahmadi24.store.dto.*;
import ir.aminahmadi24.store.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<AddProductResponse> AddProduct(
            @Valid @RequestBody AddProductRequest request){
        AddProductResponse product = productService.addProduct(request);
        URI location = URI.create("/api/v1/products/" + product.id());
        return ResponseEntity.created(location).body(product);
    }

    @PatchMapping("/{id}/quantity")
    public ResponseEntity<Void> updateQuantity(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductQuantityRequest request){
        productService.updateProductQuantity(id, request);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/price")
    public ResponseEntity<Void> updatePrice(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductPriceRequest request){
        productService.updateProductPrice(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(
            @PathVariable Long id){
        productService.removeProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<ProductDto> getAllProducts(
            @RequestParam(required = false) Short categoryId){
        return productService.getAllProducts(categoryId);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

}
