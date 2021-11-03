package trilha.back.financysdesafio07.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trilha.back.financysdesafio07.entities.Entry;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
}
