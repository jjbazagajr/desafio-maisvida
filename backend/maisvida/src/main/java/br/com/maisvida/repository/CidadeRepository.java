package br.com.maisvida.repository;

import br.com.maisvida.model.Cidade;
import br.com.maisvida.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    @Query(value = "select cidade from Cidade cidade where cidade.estado = :estado")
    List<Cidade> findByState(@Param("estado")Estado estado);

}
