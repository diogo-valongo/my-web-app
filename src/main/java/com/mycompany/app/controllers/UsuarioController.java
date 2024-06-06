package com.mycompany.app.controllers;

import com.mycompany.app.models.Usuario;
import com.mycompany.app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.mycompany.app.models.Atividade;
import com.mycompany.app.models.Edicao;
import com.mycompany.app.services.EdicaoService;
import org.springframework.http.HttpStatus;
import com.mycompany.app.services.AtividadeService;
import java.util.ArrayList;



@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EdicaoService edicaoService;

    @Autowired
    private AtividadeService atividadeService;

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.saveUsuario(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioDetails) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        usuario.setLogin(usuarioDetails.getLogin());
        usuario.setNome(usuarioDetails.getNome());
        usuario.setEmail(usuarioDetails.getEmail());
        usuario.setAfiliacao(usuarioDetails.getAfiliacao());
        usuario.setTipoUsuario(usuarioDetails.getTipoUsuario());

        Usuario updatedUsuario = usuarioService.saveUsuario(usuario);
        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{usuarioId}/organizadores")
    public ResponseEntity<Void> makeOrganizador(@PathVariable Integer usuarioId, @RequestParam Integer edicaoId, @RequestParam Integer adminId) {
        Usuario admin = usuarioService.getUsuarioById(adminId);
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        Edicao edicao = edicaoService.getEdicaoById(edicaoId);

        if (admin == null || usuario == null || edicao == null || (!usuarioService.isAdmin(admin) && !usuarioService.isOrganizador(admin, edicao)) || (!usuarioService.isParticipante(usuario, edicao))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }else if(usuarioService.isOrganizador(usuario, edicao)){
            edicao.getOrganizadores().remove(usuario);
            edicaoService.saveEdicao(edicao);
            return ResponseEntity.ok().build();
        }

        edicao.getOrganizadores().add(usuario);
        edicaoService.saveEdicao(edicao);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}/atividades")
    public ResponseEntity<List<Atividade>> getAtividadesByUsuario(@PathVariable Integer id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        List<Atividade> atividades = new ArrayList<>(usuario.getAtividadesFavoritas());
        return ResponseEntity.ok(atividades);
    }
    
    @PostMapping("/{id}/atividades/{atividadeId}")
    public ResponseEntity<Void> addAtividadeToUsuario(@PathVariable Integer id, @PathVariable Integer atividadeId) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        Atividade atividade = atividadeService.getAtividadeById(atividadeId);

        if (usuario == null || atividade == null || !usuarioService.isParticipante(usuario, atividade.getEdicao())) {
            return ResponseEntity.notFound().build();
        }
        
        //List<Atividade> atividades = new ArrayList<>(usuario.getAtividadesFavoritas());
        //atividades.add(atividade);
        //usuario.setAtividadesFavoritas(atividades);
        usuario.getAtividadesFavoritas().add(atividade);
        usuarioService.saveUsuario(usuario);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/atividades/{atividadeId}")
    public ResponseEntity<Void> DeleteAtividadeToUsuario(@PathVariable Integer id, @PathVariable Integer atividadeId) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        Atividade atividade = atividadeService.getAtividadeById(atividadeId);

        if (usuario == null || atividade == null || !usuarioService.isParticipante(usuario, atividade.getEdicao())) {
            return ResponseEntity.notFound().build();
        }

        usuario.getAtividadesFavoritas().remove(atividade);
        usuarioService.saveUsuario(usuario);
        return ResponseEntity.ok().build();
    }
}
