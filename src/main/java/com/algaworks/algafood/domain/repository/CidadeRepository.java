package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;

import java.util.List;

public interface CidadeRepository {

    List<Cidade> listarTodasAsCidades();
    Cidade buscarCidade(Long id);
    Cidade salvarCidade(Cidade cidade);
    void removerCidade(Cidade cidade);
}
