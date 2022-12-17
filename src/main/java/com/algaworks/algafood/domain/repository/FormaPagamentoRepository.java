package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.FormaPagamento;

import java.util.List;

public interface FormaPagamentoRepository {

    List<FormaPagamento> listarTodasAsFormasDePagamento();
    FormaPagamento buscarFormaDePagamento(Long id);
    FormaPagamento salvarFormaDePagamento(FormaPagamento formaPagamento);
    void removerFormaDePagamento(FormaPagamento formaPagamento);
}
