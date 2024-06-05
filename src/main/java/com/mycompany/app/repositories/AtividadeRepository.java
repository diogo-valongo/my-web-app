package com.mycompany.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mycompany.app.models.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Integer> {
}

