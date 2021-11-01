package trilha.back.financysdesafio06.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trilha.back.financysdesafio06.entities.Entry;

public interface EntryRepository extends JpaRepository<Entry, Long> {


}
