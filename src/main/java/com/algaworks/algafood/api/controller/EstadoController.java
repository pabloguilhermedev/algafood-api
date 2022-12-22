package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public List<Estado> listar(){
        return estadoRepository.listarTodosOsEstados();
    }

    @GetMapping("{id}")
    public Estado listarById(@PathVariable Long id){
        return estadoRepository.buscarEstado(id);
    }

    @PostMapping
    public ResponseEntity<Estado> salvar(@RequestBody Estado estado){
        estadoRepository.salvarEstado(estado);

        return ResponseEntity.ok().body(estado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Estado> deleter(@PathVariable Long id){
        try{
            estadoService.remover(id);

            return ResponseEntity.ok().build();

        }catch(EntidadeNaoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
