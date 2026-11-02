package ir.aminahmadi24.store.service.impl;

import ir.aminahmadi24.store.dto.*;
import ir.aminahmadi24.store.entity.CategoryEntity;
import ir.aminahmadi24.store.entity.ProductEntity;
import ir.aminahmadi24.store.enumeration.CustomError;
import ir.aminahmadi24.store.enumeration.Message;
import ir.aminahmadi24.store.exception.CategoryNotFoundException;
import ir.aminahmadi24.store.exception.ProductNotFoundException;
import ir.aminahmadi24.store.mapper.ProductMapper;
import ir.aminahmadi24.store.repository.CategoryRepository;
import ir.aminahmadi24.store.repository.ProductRepository;
import ir.aminahmadi24.store.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service

public class ProductServiceImpl implements ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public AddProductResponse addProduct(AddProductRequest request) {
        CategoryEntity categoryEntity = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(Message.CATEGORY_NOT_FOUND.name()));

        ProductEntity product = productRepository.findByNameIgnoreCase(request.getName().trim())
                .orElse(null);


        if(product == null){
            String description = request.getDescription();
            if(description == null || description.isBlank())
                description = null;
            product = new ProductEntity();
            product.setName(request.getName());
            product.setDescription(description);
            product.setPrice(request.getPrice());
            product.setQuantity(request.getQuantity());
            product.setCategory(categoryEntity);
        }else{
            product.setQuantity(product.getQuantity() + request.getQuantity());
        }

        ProductEntity savedProduct = productRepository.save(product);
        return ProductMapper.convertProductEntityToAddProductResponse(savedProduct);

    }

    @Transactional
    @Override
    public void updateProductQuantity(Long productId, UpdateProductQuantityRequest request){
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(Message.PRODUCT_NOT_FOUND.name()));
        productEntity.setQuantity(request.quantity());
    }

    @Transactional
    @Override
    public void updateProductPrice(Long productId, UpdateProductPriceRequest request) {
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(Message.PRODUCT_NOT_FOUND.name()));
        productEntity.setPrice(request.price());
    }

    @Override
    public void removeProduct(Long productId) {
        boolean exists = productRepository.existsById(productId);
        if (!exists)
            throw new ProductNotFoundException(Message.PRODUCT_NOT_FOUND.name());

        productRepository.deleteById(productId);

    }

    @Override
    public List<ProductDto> getProducts(){
        List<ProductEntity> productEntities = productRepository.findAll();
        return ProductMapper.convertProductEntityListToProductDtoList(productEntities);
    }

    @Override
    public ProductDto getProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(Message.PRODUCT_NOT_FOUND.name()));
        return ProductMapper.convertProductEntityToProductDto(productEntity);
    }
}
