package br.com.maisvida.repository;

import br.com.maisvida.model.Cidade;
import br.com.maisvida.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EstadoRepository extends JpaRepository<Estado, Integer> {


}
