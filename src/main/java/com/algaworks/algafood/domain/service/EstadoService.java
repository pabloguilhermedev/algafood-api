package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public void remover(Long id){
        try{
            estadoRepository.removerEstado(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format(
                    "O estado de codigo %d não foi encontrada", id
            ));
        }catch(DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format(
                    "O estado de código %d não pode ser excluído pois está " +
                            "em uso", id));
        }
    }
}
