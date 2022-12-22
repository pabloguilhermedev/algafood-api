package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cidade;

import java.util.List;

public interface CidadeRepository {

    List<Cidade> listarTodasAsCidades();
    Cidade buscarCidade(Long id);
    Cidade salvarCidade(Cidade cidade);
    void removerCidade(Long id);
}
