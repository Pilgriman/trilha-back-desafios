package trilha.back.financysdesafio05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import trilha.back.financysdesafio05.entities.Category;
import trilha.back.financysdesafio05.exceptions.CategoryNotFoundException;
import trilha.back.financysdesafio05.repository.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private  CategoryRepository categoryRepository;

    public Long createNewCategory(Category category) {
        Category category1 = new Category();


        category1.setName(category.getName());
        category1.setDescription(category.getDescription());

        category1 = categoryRepository.save(category1);

        return category1.getId();
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        Category requestedCategory = categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException(String.format("Category with id: '%s' not found" , id)));

        return requestedCategory;
    }

    @Transactional
    public Category updateCategory(Long id, Category categoryToUpdateRequest){
        Category categoryFromDatabase = categoryRepository.findById(id).orElseThrow(()->new CategoryNotFoundException(String.format("Category with id: '%s' not found" , id)));

        Category categoryToUpdate = categoryFromDatabase;

        categoryToUpdate.setName(categoryToUpdateRequest.getName());
        categoryToUpdate.setDescription(categoryToUpdateRequest.getDescription());

        categoryRepository.save(categoryToUpdate);

        return categoryToUpdate;

    }


    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }


    private Category validateCategory (Long id){
        Category category = categoryRepository.findById(id).orElseThrow(()->new CategoryNotFoundException(String.format("Category with id: '%s' not found" , id)));
        return category;
    }
}
