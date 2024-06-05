package com.mycompany.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.mycompany.app.models.Edicao;
import com.mycompany.app.repositories.EdicaoRepository;
import com.mycompany.app.models.Usuario;
import java.util.Collections;

@Service
public class EdicaoService {

    @Autowired
    private EdicaoRepository edicaoRepository;

    public List<Edicao> getAllEdicoes() {
        return edicaoRepository.findAll();
    }

    public Edicao getEdicaoById(Integer id) {
        return edicaoRepository.findById(id).orElse(null);
    }

    public Edicao saveEdicao(Edicao edicao) {
        return edicaoRepository.save(edicao);
    }

    public void deleteEdicao(Integer id) {
        edicaoRepository.deleteById(id);
    }

    public List<Usuario> getOrganizadoresByEdicao(Integer edicaoId) {
        Edicao edicao = getEdicaoById(edicaoId);
        if (edicao != null) {
            return edicao.getOrganizadores();
        }
        return Collections.emptyList();
    }

}
