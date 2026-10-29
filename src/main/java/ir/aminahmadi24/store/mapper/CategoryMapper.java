package ir.aminahmadi24.store.mapper;

import ir.aminahmadi24.store.dto.CategoryDto;
import ir.aminahmadi24.store.entity.CategoryEntity;

public class CategoryMapper
{
    public static CategoryDto convertCategoryEntityToCategoryDto(CategoryEntity categoryEntity){
        return new CategoryDto(
                categoryEntity.getId(),
                categoryEntity.getName(),
                categoryEntity.getCreatedAt(),
                categoryEntity.getUpdatedAt()
        );
    }
}
