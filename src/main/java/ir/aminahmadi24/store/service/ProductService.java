package ir.aminahmadi24.store.service;

import ir.aminahmadi24.store.dto.AddProductRequest;
import ir.aminahmadi24.store.dto.AddProductResponse;
import ir.aminahmadi24.store.dto.UpdateProductPriceRequest;
import ir.aminahmadi24.store.dto.UpdateProductQuantityRequest;

public interface ProductService {
    AddProductResponse addProduct(AddProductRequest request);
    void updateProductQuantity(Long productId, UpdateProductQuantityRequest request);
    void updateProductPrice(Long productId, UpdateProductPriceRequest request);
}
