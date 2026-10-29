package ir.aminahmadi24.store.service.impl;

import ir.aminahmadi24.store.dto.CategoryDto;
import ir.aminahmadi24.store.dto.CreateCategoryRequest;
import ir.aminahmadi24.store.entity.CategoryEntity;
import ir.aminahmadi24.store.enumeration.CustomError;
import ir.aminahmadi24.store.enumeration.Message;
import ir.aminahmadi24.store.exception.CategoryAlreadyExistsException;
import ir.aminahmadi24.store.mapper.CategoryMapper;
import ir.aminahmadi24.store.repository.CategoryRepository;
import ir.aminahmadi24.store.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CreateCategoryRequest request) {
        String categoryName = request.name().trim().toLowerCase();
        boolean existsByName = categoryRepository.existsByName(categoryName);

        if (existsByName)
            throw new CategoryAlreadyExistsException(Message.CATEGORY_ALREADY_EXISTS.name());

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryName);
        CategoryEntity savedCategory = categoryRepository.save(categoryEntity);
        return CategoryMapper.convertCategoryEntityToCategoryDto(savedCategory);
    }
}
