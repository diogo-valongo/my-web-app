package com.mycompany.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mycompany.app.models.Edicao;

public interface EdicaoRepository extends JpaRepository<Edicao, Integer> {
}
