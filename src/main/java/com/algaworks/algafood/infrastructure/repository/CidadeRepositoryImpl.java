package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cidade> listarTodasAsCidades(){
        return manager.createQuery("from Cidade", Cidade.class)
                .getResultList();
    }

    @Override
    public Cidade buscarCidade(Long id){
        return manager.find(Cidade.class, id);
    }

    @Transactional
    @Override
    public Cidade salvarCidade(Cidade cidade){
        return manager.merge(cidade);
    }

    @Transactional
    @Override
    public void removerCidade(Long id){
        Cidade cidade = buscarCidade(id);
        manager.remove(cidade);
    }
}
