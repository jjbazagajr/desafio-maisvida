package br.com.maisvida.resource;

import br.com.maisvida.model.Estado;
import br.com.maisvida.model.JsonResponse;
import br.com.maisvida.repository.CidadeRepository;
import br.com.maisvida.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jjbaz on 20/01/2018.
 */
@RestController
@RequestMapping("/cidade")
public class CidadeResource {

    @Autowired
    private JsonResponse jsonResponse;

    @Autowired
    private CidadeRepository repository;

    @Autowired
    private EstadoRepository estadoRepository;

    /**
     * Método para listar especialidades
     *
     * @return JsonResponse  - objeto e dados do request
     */
    @GetMapping("/listar/{estadoId}")
    private JsonResponse listar(@PathVariable int estadoId) {

        Estado estado = estadoRepository.findOne(estadoId);
        jsonResponse.setData(repository.findByState(estado));
        jsonResponse.setCode(Integer.valueOf(HttpStatus.OK.toString()));
        jsonResponse.setMessage("Lista obtida com sucesso!");

        return jsonResponse;

    }
}
