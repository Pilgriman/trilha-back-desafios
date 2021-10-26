package trilha.back.financysdesafio05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trilha.back.financysdesafio05.entities.Category;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findById(Category categoryId);
}
