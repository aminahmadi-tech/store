package ir.aminahmadi24.store.service;

import ir.aminahmadi24.store.dto.*;

import java.util.List;

public interface ProductService {
    AddProductResponse addProduct(AddProductRequest request);
    void updateProductQuantity(Long productId, UpdateProductQuantityRequest request);
    void updateProductPrice(Long productId, UpdateProductPriceRequest request);
    void removeProduct(Long productId);
    List<ProductDto> getAllProducts(Short categoryId);
    ProductDto getProductById(Long id);
//    List<ProductDto> getProductsByCategoryId(Short categoryId);
}
