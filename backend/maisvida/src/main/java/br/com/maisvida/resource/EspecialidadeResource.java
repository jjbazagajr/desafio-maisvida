package br.com.maisvida.resource;

import br.com.maisvida.model.JsonResponse;
import br.com.maisvida.repository.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jjbaz on 20/01/2018.
 */
@RestController
@RequestMapping("/especialidade")
public class EspecialidadeResource {

    @Autowired
    private JsonResponse jsonResponse;

    @Autowired
    private EspecialidadeRepository repository;

    /**
     * Método para listar especialidades
     *
     * @return JsonResponse  - objeto e dados do request
     */
    @GetMapping("/listar")
    private JsonResponse listar() {

        jsonResponse.setData(repository.findAll());
        jsonResponse.setCode(Integer.valueOf(HttpStatus.OK.toString()));
        jsonResponse.setMessage("Lista obtida com sucesso!");

        return jsonResponse;

    }
}
