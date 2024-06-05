package com.mycompany.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mycompany.app.models.Espaco;

public interface EspacoRepository extends JpaRepository<Espaco, Integer> {
}
