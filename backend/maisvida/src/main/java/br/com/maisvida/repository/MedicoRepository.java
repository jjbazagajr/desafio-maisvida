package br.com.maisvida.repository;

import br.com.maisvida.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    @Query(value = "select m from Medico m")
    Page<Medico> listarPaginado(Pageable pageable);

}
