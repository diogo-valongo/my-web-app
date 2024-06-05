package com.mycompany.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mycompany.app.models.Evento;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
}

