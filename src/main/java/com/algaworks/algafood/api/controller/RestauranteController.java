package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @GetMapping
    public List<Restaurante> listar(){
        return restauranteRepository.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id){

        Restaurante restaurante = restauranteRepository.buscar(id);

        if(restaurante != null) {
            return ResponseEntity.ok(restaurante);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar
            (@RequestBody Restaurante restaurante){

        try{
            cadastroRestauranteService.salvar(restaurante);

            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);

        }catch(EntidadeNaoEncontradaException e){

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id,
                                                 @RequestBody Restaurante restaurante){

        Restaurante restauranteAtual = restauranteRepository.buscar(id);

        BeanUtils.copyProperties(restaurante, restauranteAtual, "id");

        try {
            cadastroRestauranteService.salvar(restauranteAtual);

            return ResponseEntity.ok(restauranteAtual);

        }catch(EntidadeNaoEncontradaException e){

            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
