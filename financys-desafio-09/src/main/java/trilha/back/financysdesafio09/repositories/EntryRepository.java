package trilha.back.financysdesafio09.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trilha.back.financysdesafio09.entities.Entry;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
}
