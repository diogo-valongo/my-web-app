package com.mycompany.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.mycompany.app.models.Espaco;
import com.mycompany.app.repositories.EspacoRepository;

@Service
public class EspacoService {

    @Autowired
    private EspacoRepository espacoRepository;

    public List<Espaco> getAllEspacos() {
        return espacoRepository.findAll();
    }

    public Espaco getEspacoById(Integer id) {
        return espacoRepository.findById(id).orElse(null);
    }

    public Espaco saveEspaco(Espaco espaco) {
        return espacoRepository.save(espaco);
    }

    public void deleteEspaco(Integer id) {
        espacoRepository.deleteById(id);
    }
}
