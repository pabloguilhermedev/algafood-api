package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Estado> listarTodosOsEstados(){
        return manager.createQuery("from Estado", Estado.class)
                .getResultList();
    }

    @Override
    public Estado buscarEstado(Long id){
        return manager.find(Estado.class, id);
    }

    @Transactional
    @Override
    public Estado salvarEstado(Estado estado){
        return manager.merge(estado);
    }

    @Transactional
    @Override
    public void removerEstado(Estado estado){
        estado = buscarEstado(estado.getId());
        manager.remove(estado);
    }
}
