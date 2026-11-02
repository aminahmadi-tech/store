package ir.aminahmadi24.store.controller;

import ir.aminahmadi24.store.dto.AddProductRequest;
import ir.aminahmadi24.store.dto.AddProductResponse;
import ir.aminahmadi24.store.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
