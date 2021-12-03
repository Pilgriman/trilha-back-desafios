package trilha.back.financysdesafio12.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trilha.back.financysdesafio12.dto.CategoryDto;
import trilha.back.financysdesafio12.entities.Category;
import trilha.back.financysdesafio12.exceptions.CategoryAlreadyRegisteredException;
import trilha.back.financysdesafio12.exceptions.CategoryNotFoundException;
import trilha.back.financysdesafio12.mappers.CategoryMapper;
import trilha.back.financysdesafio12.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;


    public CategoryDto create(CategoryDto categoryDto) throws CategoryAlreadyRegisteredException {

        verifyIfIsAlreadyRegistered(categoryDto.getName());
        categoryRepository.save(categoryMapper.mapToCategory(categoryDto));
        log.info("Category Saved " + categoryDto.getName());
        return categoryDto;

    }

    public CategoryDto get(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category Not Found."));
        log.info("Get Category: "+category.getCategoryName());
        return categoryMapper.mapCategoryToDto(category);
    }


    public List<CategoryDto> getAll() {
        log.info("Categories...");
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::mapCategoryToDto).collect(Collectors.toList());
    }


    public CategoryDto update(Long id) {

        CategoryDto categoryDto = new CategoryDto();
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category Not found"));
        category.setCategoryName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        categoryRepository.save(category);

                return categoryMapper.mapCategoryToDto(category);

    }

    public void delete(Long id) {
        log.info("Category Removed");
        categoryRepository.deleteById(id);
    }

    private void verifyIfIsAlreadyRegistered(String name) throws CategoryAlreadyRegisteredException{
        Category optSavedCategory = categoryRepository.findByCategoryName(name);
        if (optSavedCategory != null){
            throw new CategoryAlreadyRegisteredException(name);
        }
    }

}
