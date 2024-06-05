package com.mycompany.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mycompany.app.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
