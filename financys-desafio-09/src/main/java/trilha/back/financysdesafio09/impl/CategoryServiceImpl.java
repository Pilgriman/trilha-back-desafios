package trilha.back.financysdesafio09.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trilha.back.financysdesafio09.dto.CategoryDto;
import trilha.back.financysdesafio09.entities.Category;
import trilha.back.financysdesafio09.exceptions.CategoryNotFoundException;
import trilha.back.financysdesafio09.mapper.CategoryMapper;
import trilha.back.financysdesafio09.repositories.CategoryRepository;
import trilha.back.financysdesafio09.service.CategoryService;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        categoryRepository.save(categoryMapper.mapToCategory(categoryDto));
        return categoryDto;
    }

    @Override
    public CategoryDto get(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category Not Found."));
        System.out.println("Get Category: "+category.getCategoryName());
        return categoryMapper.mapCategoryToDto(category);
    }

    @Override
    public List<CategoryDto> getAll() {
        System.out.println("Categories...");
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::mapCategoryToDto).collect(Collectors.toList());
    }

    @Override
    public void update(Long id) {

        CategoryDto categoryDto = new CategoryDto();
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category Not found"));
        category.setCategoryName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        categoryRepository.save(category);

    }

    @Override
    public void delete(Long id) {
        System.out.println("Category Removed");
        categoryRepository.deleteById(id);
    }
}
