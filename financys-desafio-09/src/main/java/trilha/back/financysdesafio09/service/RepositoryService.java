package trilha.back.financysdesafio09.service;

import java.util.List;

public interface RepositoryService<T> extends Service {

    List<T>getAll();
    T get(Long id);
    T save(T dto);
    void update(Long id);
    void delete(Long id);


}
