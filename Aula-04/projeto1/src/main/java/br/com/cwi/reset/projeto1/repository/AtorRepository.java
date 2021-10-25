package br.com.cwi.reset.projeto1.repository;

import br.com.cwi.reset.projeto1.domain.Ator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtorRepository extends CrudRepository {

    Ator findByNameIgnoringCase (String nome);
    Ator findById (Integer id);

}
