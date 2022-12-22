package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public void excluir(Long id){
        try{
            cidadeRepository.removerCidade(id);
        }catch(EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format(
                    "A cidade de código %d não foi encontrada", id
            ));
        }catch(DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format(
                    "A cidade de código %d não pode ser excluída " +
                            "pois está em uso", id
            ));
        }
    }

    public Cidade salvar(Cidade cidade){

        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoRepository.buscarEstado(estadoId);

        if(estado == null){
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de estado com o código %d", estadoId)
            );
        }

        cidade.setEstado(estado);

        return cidadeRepository.salvarCidade(cidade);
    }
}
