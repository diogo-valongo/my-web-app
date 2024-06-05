package com.mycompany.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.mycompany.app.models.Usuario;
import com.mycompany.app.repositories.UsuarioRepository;
import com.mycompany.app.models.enums.TipoUsuario;
import com.mycompany.app.models.Edicao;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public boolean isAdmin(Usuario usuario) {
        return usuario.getTipoUsuario() == TipoUsuario.ADMIN;
    }

    public boolean isOrganizador(Usuario usuario, Edicao edicao) {
        return edicao.getOrganizadores().contains(usuario);
    }

    public boolean isParticipante(Usuario usuario, Edicao edicao) {
        return edicao.getUsuarios().contains(usuario);
    }
}
