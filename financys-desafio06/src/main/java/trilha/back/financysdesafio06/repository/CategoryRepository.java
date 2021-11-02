package trilha.back.financysdesafio06.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trilha.back.financysdesafio06.entities.Category;
import trilha.back.financysdesafio06.entities.Entry;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findById(Category categoryId);

    Category findByCategoryName(String categoryName);
}
