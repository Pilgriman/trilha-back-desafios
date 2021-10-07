package trilha.back.financys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trilha.back.financys.category.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
