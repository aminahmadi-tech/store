package ir.aminahmadi24.store.controller;

import ir.aminahmadi24.store.dto.CategoryDto;
import ir.aminahmadi24.store.dto.CreateCategoryRequest;
import ir.aminahmadi24.store.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> create(@Valid @RequestBody CreateCategoryRequest request){
        CategoryDto category = categoryService.createCategory(request);
        URI location = URI.create("/api/v1/categories/" + category.id());
        return  ResponseEntity.created(location).body(category);
    }

}
