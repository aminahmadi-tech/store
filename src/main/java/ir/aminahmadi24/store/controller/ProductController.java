package ir.aminahmadi24.store.controller;

import ir.aminahmadi24.store.dto.AddProductRequest;
import ir.aminahmadi24.store.dto.AddProductResponse;
import ir.aminahmadi24.store.dto.UpdateProductQuantityRequest;
import ir.aminahmadi24.store.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
}
