package trilha.back.financysdesafio12.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trilha.back.financysdesafio12.dto.EntryDTO;
import trilha.back.financysdesafio12.entities.Entry;

import java.util.List;


@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {}
