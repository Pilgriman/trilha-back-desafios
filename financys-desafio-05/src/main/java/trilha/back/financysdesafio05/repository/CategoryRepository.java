package trilha.back.financysdesafio05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trilha.back.financysdesafio05.entities.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {

}
