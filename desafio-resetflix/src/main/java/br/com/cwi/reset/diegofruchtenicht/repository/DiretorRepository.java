package br.com.cwi.reset.diegofruchtenicht.repository;

import br.com.cwi.reset.diegofruchtenicht.model.Diretor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DiretorRepository extends CrudRepository <Diretor, Integer> {

    List<Diretor> findAll ();
    Diretor findByNomeIgnoringCase (String nome);

}
