package trilha.back.financysdesafio05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trilha.back.financysdesafio05.entities.Entry;

import java.util.Optional;

public interface EntryRepository extends JpaRepository<Entry, Long> {

}
