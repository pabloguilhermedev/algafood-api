package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public List<Cidade> listar(){
        return cidadeRepository.listarTodasAsCidades();
    }

    @GetMapping("{id}")
    public Cidade buscarPorId(@PathVariable Long id){
        return cidadeRepository.buscarCidade(id);
    }

    @PostMapping
    public ResponseEntity<Cidade> salvar(@RequestBody Cidade cidade){
        cidadeRepository.salvarCidade(cidade);

        return ResponseEntity.ok().body(cidade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> salvar(@PathVariable Long id){
        try{
            cidadeService.excluir(id);
            return ResponseEntity.ok().build();

        }catch(EntidadeEmUsoException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }catch(EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id,
                                       @RequestBody Cidade cidade){

        Cidade cidadeAtual = cidadeRepository.buscarCidade(id);

        BeanUtils.copyProperties(cidade, cidadeAtual, "id");

        try{
            cidadeService.salvar(cidade);

            return ResponseEntity.ok().body(cidade);

        }catch(EntidadeNaoEncontradaException e){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
