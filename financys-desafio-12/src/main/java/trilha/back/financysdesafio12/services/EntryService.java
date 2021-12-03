package trilha.back.financysdesafio12.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import trilha.back.financysdesafio12.dto.EntryDTO;
import trilha.back.financysdesafio12.entities.Category;
import trilha.back.financysdesafio12.entities.Entry;
import trilha.back.financysdesafio12.exceptions.EntryAlreadyRegisteredException;
import trilha.back.financysdesafio12.exceptions.EntryNotFoundException;
import trilha.back.financysdesafio12.exceptions.EntryNullResquest;
import trilha.back.financysdesafio12.exceptions.NoContentException;
import trilha.back.financysdesafio12.mappers.CategoryMapper;
import trilha.back.financysdesafio12.mappers.EntryMapper;
import trilha.back.financysdesafio12.repositories.CategoryRepository;
import trilha.back.financysdesafio12.repositories.EntryRepository;


import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private EntryMapper entryMapper;

    @Autowired
    private EntryRepository entryRepository;

    public EntryDTO save(EntryDTO entryDTO) throws EntryAlreadyRegisteredException {
        Category category = categoryRepository.findByCategoryName(entryDTO.getCategoryId().getCategoryName());

        Entry entry = entryMapper.toModel(entryDTO);
        entry.setCategoryId(category);
        Entry saveEntry = entryRepository.save(entry);
        return entryMapper.toDTO(saveEntry);

    }


    public List<EntryDTO> getAll() {
        log.info("Get all entries");
        return entryRepository.findAll()
                .stream()
                .map(entryMapper::toDTO).collect(Collectors.toList());
    }


    public EntryDTO get(Long id) {
        log.info("Get Entries");
        Entry requestedEntry = entryRepository.findById(id).orElseThrow(() -> new EntryNotFoundException(String.format("Entry with id: '%s' not found", id)));

        return entryMapper.toDTO(requestedEntry);
    }

    public EntryDTO update(Long id) {

        EntryDTO entryDTO = new EntryDTO();
        Entry entry = entryRepository.findById(id).orElseThrow(() -> new EntryNotFoundException("Entry not found"));
        entry.setName(entryDTO.getName());
        entry.setAmount(entryDTO.getAmount());

        Category category = categoryRepository.findByCategoryName(entryDTO.getName());
        entry.setCategoryId(category);

        log.info("Entry Updated "+ entry);
        entryRepository.save(entry);

        log.info("Entry Updated " + entry);
        entryRepository.save(entry);

        return entryMapper.toDTO(entry);
    }


    public void delete(Long id) {
        log.info("Entry Removed");
        entryRepository.deleteById(id);

    }


    public List<EntryDTO> getLancamentosDependentes(String date,String amount, boolean paid) throws NoContentException, EntryNullResquest {

        //1 passo: verficar se os dados inputados pelo usuario são nulos

        if (date == null || amount == null) {
            //2 Em caso de dados nulos, lançar exception(code 400)
            throw new EntryNullResquest("Entry is Null ");

        }

            //3 passo: fazer um filter e converter os dados para DTO

            List<EntryDTO> entriesDTO = entryRepository.findAll()
                    .stream()
                    .filter(entry -> entry.getDate().equals(date) &&
                            entry.getAmount().equals(amount) && entry.getPaid() == paid)
                    .map(r-> entryMapper.toDTO(r))
                    .collect(Collectors.toList());




        //4 passo: em caso de lista vazia , lançar exception(code 204)

        if (CollectionUtils.isEmpty(entriesDTO)) {
            throw new NoContentException("Entry is Empty ");

        }
        //5 passo: retornar os dados filtrados
         return entriesDTO;
    }

}














