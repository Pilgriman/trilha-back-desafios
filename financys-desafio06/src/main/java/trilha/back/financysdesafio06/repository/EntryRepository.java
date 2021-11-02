package trilha.back.financysdesafio06.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trilha.back.financysdesafio06.entities.Entry;


@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {


}
