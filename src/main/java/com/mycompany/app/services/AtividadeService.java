package com.mycompany.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.mycompany.app.models.Atividade;
import com.mycompany.app.repositories.AtividadeRepository;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    public List<Atividade> getAllAtividades() {
        return atividadeRepository.findAll();
    }

    public Atividade getAtividadeById(Integer id) {
        return atividadeRepository.findById(id).orElse(null);
    }

    public Atividade saveAtividade(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    public void deleteAtividade(Integer id) {
        atividadeRepository.deleteById(id);
    }
}
