package br.com.cwi.reset.diegofruchtenicht.repository;

import br.com.cwi.reset.diegofruchtenicht.model.Diretor;
import br.com.cwi.reset.diegofruchtenicht.model.Filme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FilmeRepository extends CrudRepository <Filme, Integer> {

    List<Filme> findAll ();
    List<Filme> findByDiretor (Diretor diretor);

}
