package ir.aminahmadi24.store.service;

import ir.aminahmadi24.store.dto.CategoryDto;
import ir.aminahmadi24.store.dto.CreateCategoryRequest;

public interface CategoryService {
    CategoryDto createCategory(CreateCategoryRequest request);
}
