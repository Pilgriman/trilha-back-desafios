package trilha.back.financysdesafio12.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trilha.back.financysdesafio12.entities.Category;

import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryName(String categoryName);




}