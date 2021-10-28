package br.com.cwi.reset.diegofruchtenicht.repository;

import br.com.cwi.reset.diegofruchtenicht.model.Ator;
import br.com.cwi.reset.diegofruchtenicht.model.Diretor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AtorRepository extends CrudRepository <Ator,Integer>{

    List<Ator> findAll ();
    Ator findByNomeIgnoringCase (String nome);

}
