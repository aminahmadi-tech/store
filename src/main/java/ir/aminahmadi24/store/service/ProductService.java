package ir.aminahmadi24.store.service;

import ir.aminahmadi24.store.dto.AddProductRequest;
import ir.aminahmadi24.store.dto.AddProductResponse;

public interface ProductService {
    AddProductResponse addProduct(AddProductRequest request);
}
