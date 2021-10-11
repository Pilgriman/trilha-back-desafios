package trilha.back.financysdesafio05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trilha.back.financysdesafio05.entities.Category;
import trilha.back.financysdesafio05.exceptions.CategoryNotFoundException;
import trilha.back.financysdesafio05.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private  CategoryRepository categoryRepository;

    public Long createNewCategory(Category category) {
        Category category1 = new Category();

        category1.setId(category.getId());
        category1.setName(category.getName());
        category1.setDescription(category.getDescription());

        category1 = categoryRepository.save(category1);

        return category1.getId();
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        Optional<Category> requestedCategory = categoryRepository.findById(id);
        if (requestedCategory.isEmpty()) {
            throw new CategoryNotFoundException(String.format("Category with id: '%s' not found" , id));

        }

        return requestedCategory.get();
    }

    @Transactional
    public Category updateCategory(Long id, Category categoryToUpdateRequest){
        Optional<Category> categoryFromDatabase = categoryRepository.findById(id);
        if (categoryFromDatabase.isEmpty()){
            throw new CategoryNotFoundException(String.format("Category with id: '%s' not found" , id));
        }

        Category categoryToUpdate = categoryFromDatabase.get();

        categoryToUpdate.setId(categoryToUpdateRequest.getId());
        categoryToUpdate.setName(categoryToUpdateRequest.getName());
        categoryToUpdate.setDescription(categoryToUpdateRequest.getDescription());

        return categoryToUpdate;

    }
    //método pedido no item II da segunda questão
    public boolean validateCategoryById(CategoryRepository categoryRepository, Category category){
        for(Category content : categoryRepository.findAll()){
            if(content.getId() == category.getId()){
                return true;
            }
        }
        return false;
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
