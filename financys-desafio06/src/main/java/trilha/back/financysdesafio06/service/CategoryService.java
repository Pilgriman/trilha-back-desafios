package trilha.back.financysdesafio06.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import trilha.back.financysdesafio06.dto.CategoryDto;
import trilha.back.financysdesafio06.entities.Category;
import trilha.back.financysdesafio06.exceptions.CategoryNotFoundException;
import trilha.back.financysdesafio06.mapper.CategoryMapper;
import trilha.back.financysdesafio06.repository.CategoryRepository;



import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public void addCategory(CategoryDto categoryDto) {
        categoryRepository.save(categoryMapper.mapToCategory(categoryDto));
        //System.out.println(("Category Saved: "+categoryDto.getName()));
    }

    public CategoryDto getCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category Not Found."));
        //System.out.println("Get Category: "+category.getCategoryName());
        return categoryMapper.mapCategoryToDto(category);
    }

    public List<CategoryDto> getAllCategories() {
        System.out.println("Categories...");
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::mapCategoryToDto).collect(Collectors.toList());
    }

    public void updateCategory(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category Not found"));
        category.setCategoryName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        categoryRepository.save(category);

    }

    public void deleteCategory(Long id) {
        System.out.println("Category Removed");
        categoryRepository.deleteById(id);
    }

}
