package ir.aminahmadi24.store.mapper;

import ir.aminahmadi24.store.dto.AddProductResponse;
import ir.aminahmadi24.store.entity.ProductEntity;

public class ProductMapper {
    public static AddProductResponse convertProductEntityToAddProductResponse(ProductEntity productEntity){
        return new AddProductResponse(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getPrice(),
                productEntity.getQuantity(),
                productEntity.getCategory().getId()
        );
    }
}
