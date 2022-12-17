package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<FormaPagamento> listarTodasAsFormasDePagamento(){
        return manager.createQuery("from FormaPagamento", FormaPagamento.class)
                .getResultList();
    }

    @Override
    public FormaPagamento buscarFormaDePagamento(Long id){
        return manager.find(FormaPagamento.class, id);
    }

    @Transactional
    @Override
    public FormaPagamento salvarFormaDePagamento(FormaPagamento formaPagamento){
        return manager.merge(formaPagamento);
    }

    @Transactional
    @Override
    public void removerFormaDePagamento(FormaPagamento formaPagamento){
        formaPagamento = buscarFormaDePagamento(formaPagamento.getId());
        manager.remove(formaPagamento);
    }
}
