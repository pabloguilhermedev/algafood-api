package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Permissao> listarTodasAsPermissoes(){
        return manager.createQuery("from Permissao", Permissao.class)
                .getResultList();
    }

    @Override
    public Permissao buscarPermissao(Long id){
        return manager.find(Permissao.class, id);
    }

    @Transactional
    @Override
    public Permissao salvarPermissao(Permissao permissao){
        return manager.merge(permissao);
    }

    @Transactional
    @Override
    public void removerPermissao(Permissao permissao){
        permissao = buscarPermissao(permissao.getId());
        manager.remove(permissao);
    }
}
