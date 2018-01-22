package br.com.maisvida.resource;

import br.com.maisvida.model.JsonResponse;
import br.com.maisvida.model.Medico;
import br.com.maisvida.repository.MedicoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;
import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * Created by jjbaz on 19/01/2018.
 */
@RestController
@RequestMapping("/medico")
public class MedicoResource {

    @Autowired
    private MedicoRepository repository;

    @Autowired
    private JsonResponse jsonResponse ;

    /**
     * Método para salvar médico
     *
     * @return JsonResponse  - objeto e dados do request
     */
    @PostMapping(value = "/salvar")
    private JsonResponse save(@Valid @RequestBody Medico medico, Errors errors) {


        try {
            //Verifica se tem erros de validação
            if (errors.hasErrors()) {

                //Concatena todos os erros com delimitador ","
                jsonResponse.setMessage(errors.getAllErrors()
                        .stream()
                        .map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));

                jsonResponse.setCode(Integer.valueOf(HttpStatus.BAD_REQUEST.toString()));

                return jsonResponse;
            }

            Medico medicoSalvo = repository.save(medico);
            repository.flush();
            jsonResponse.setMessage("Médico salvo com sucesso!");
            jsonResponse.setData(medicoSalvo);
            jsonResponse.setCode(Integer.valueOf(HttpStatus.OK.toString()));


        } catch (PersistenceException e) {

            jsonResponse.setCode(Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.toString()));
            jsonResponse.setMessage("Erro no banco de dados.." + "\n error: " + e.getMessage());

        } catch (Exception e) {

            jsonResponse.setCode(Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.toString()));
            jsonResponse.setMessage(e.getMessage());
        }

        return jsonResponse;
    }

    /**
     * Método para listar médicos
     *
     * @return JsonResponse  - objeto e dados do request
     * @param pageable - parametros de paginação
     */
    @GetMapping("/listar")
    private JsonResponse listarPaginado(Pageable pageable) {


        jsonResponse.setData(repository.listarPaginado(pageable));
        jsonResponse.setCode(Integer.valueOf(HttpStatus.OK.toString()));
        jsonResponse.setMessage("Lista obtida com sucesso!");

        return jsonResponse;

    }

    /**
     * Método para detalhar médico especifico
     *
     * @return JsonResponse  - objeto e dados do request
     * @param medicoId - Id do médico a ser detalhado
     */
    @GetMapping("/detalhar/{medicoId}")
    private JsonResponse detalhar(@PathVariable int medicoId) {

        Medico medicoDB = repository.findOne(medicoId);

        jsonResponse.setData(medicoDB);

        if(medicoDB == null) {
            jsonResponse.setMessage("Médico não encontrado!");
            jsonResponse.setCode(Integer.valueOf(HttpStatus.NOT_FOUND.toString()));

            return jsonResponse;
        }

        jsonResponse.setCode(Integer.valueOf(HttpStatus.OK.toString()));
        return jsonResponse;

    }

    /**
     * Método para remover médico
     *
     * @return JsonResponse  - objeto e dados do request
     * @param medicoId  - Id do médico
     */
    @DeleteMapping("/deletar/{medicoId}")
    public JsonResponse deleteMethod(@PathVariable int medicoId) {
        Medico medico = repository.findOne(medicoId);

        jsonResponse.setData(medico);

        if(medico == null) {
            jsonResponse.setMessage("Médico não encontrado!");
            jsonResponse.setCode(Integer.valueOf(HttpStatus.NOT_FOUND.toString()));
        } else {
            repository.delete(medico);
            jsonResponse.setCode(Integer.valueOf(HttpStatus.OK.toString()));
            jsonResponse.setMessage("Médico deletado com sucesso!");
            jsonResponse.setData(null);
        }

        return jsonResponse;
    }

    /**
     * Método para listar médicos
     *
     * @return JsonResponse  - objeto e dados do request
     * @param medicoId - id do médico a ser alterado
     * @param medico - json com as alterações
     */

    @PutMapping("/editar/{medicoId}")
    public JsonResponse deleteMethod(@PathVariable int medicoId, @Valid @RequestBody Medico medico, Errors errors) {

        Medico medicoDB = repository.findOne(medicoId);

        jsonResponse.setData(medicoDB);

        if(medicoDB == null) {
            jsonResponse.setMessage("Médico não encontrado!");
            jsonResponse.setCode(Integer.valueOf(HttpStatus.NOT_FOUND.toString()));

            return jsonResponse;
        }

        //Verifica se tem erros de validação
        if (errors.hasErrors()) {

            //Concatena todos os erros com delimitador ","
            jsonResponse.setMessage(errors.getAllErrors()
                    .stream()
                    .map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            jsonResponse.setCode(Integer.valueOf(HttpStatus.BAD_REQUEST.toString()));
            jsonResponse.setData(null);

            return jsonResponse;
        }

        BeanUtils.copyProperties(medico, medicoDB, "id");
        repository.save(medicoDB);
        jsonResponse.setMessage("Médico atualizado com sucesso!");
        jsonResponse.setCode(Integer.valueOf(HttpStatus.OK.toString()));

        return jsonResponse;

    }



}
