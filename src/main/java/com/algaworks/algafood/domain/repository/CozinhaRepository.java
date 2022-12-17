package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CozinhaRepository {

    List<Cozinha> listarTodasAsCozinhas();
    Cozinha buscarCozinha(Long id);
    Cozinha salvarCozinha(Cozinha cozinha);
    void removerCozinha(Cozinha cozinha);

}
