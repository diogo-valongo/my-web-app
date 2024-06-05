package com.mycompany.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.mycompany.app.models.Evento;
import com.mycompany.app.repositories.EventoRepository;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    public Evento getEventoById(Integer id) {
        return eventoRepository.findById(id).orElse(null);
    }

    public Evento saveEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public void deleteEvento(Integer id) {
        eventoRepository.deleteById(id);
    }
}
