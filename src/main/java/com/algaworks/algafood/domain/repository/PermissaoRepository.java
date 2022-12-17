package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Permissao;

import java.util.List;

public interface PermissaoRepository {

    List<Permissao> listarTodasAsPermissoes();
    Permissao buscarPermissao(Long id);
    Permissao salvarPermissao(Permissao permissao);
    void removerPermissao(Permissao permissao);
}
