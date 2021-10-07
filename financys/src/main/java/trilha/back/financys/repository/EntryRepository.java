package trilha.back.financys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trilha.back.financys.entry.Entry;

public interface EntryRepository extends JpaRepository<Entry, Long> {
}
