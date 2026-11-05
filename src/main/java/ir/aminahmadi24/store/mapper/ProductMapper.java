package ir.aminahmadi24.store.mapper;

import ir.aminahmadi24.store.dto.AddProductResponse;
import ir.aminahmadi24.store.dto.ProductDto;
import ir.aminahmadi24.store.dto.ProductDtoV2;
import ir.aminahmadi24.store.entity.ProductEntity;

import java.util.List;

public class ProductMapper {
    public static AddProductResponse convertProductEntityToAddProductResponse(ProductEntity productEntity) {
        return new AddProductResponse(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getPrice(),
                productEntity.getQuantity(),
                productEntity.getCategory().getId()
        );
    }

    public static ProductDto convertProductEntityToProductDto(ProductEntity productEntity) {
        return new ProductDto(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getPrice(),
                productEntity.getQuantity(),
                productEntity.getCategory().getId()
        );
    }

    public static List<ProductDto> convertProductEntityListToProductDtoList(List<ProductEntity> productEntities) {
        return productEntities.stream()
                .map((ProductMapper::convertProductEntityToProductDto))
                .toList();
    }

    public static ProductDtoV2 convertProductEntityToProductDtoV2(ProductEntity productEntity) {
        return new ProductDtoV2(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice(),
                productEntity.getQuantity()
        );
    }
}
