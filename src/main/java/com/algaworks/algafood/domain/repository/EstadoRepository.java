package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {

    List<Estado> listarTodosOsEstados();
    Estado buscarEstado(Long id);
    Estado salvarEstado(Estado estado);
    void removerEstado(Estado estado);
}
