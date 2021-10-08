package trilha.back.financys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trilha.back.financys.entities.Entry;

public interface EntryRepository extends JpaRepository<Entry, Long> {
}
