package com.mycompany.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mycompany.app.models.Espaco;
import com.mycompany.app.services.EspacoService;
import com.mycompany.app.models.Edicao;
import com.mycompany.app.services.EdicaoService;
import com.mycompany.app.models.Usuario;
import com.mycompany.app.services.UsuarioService;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/espacos")
public class EspacoController {

    @Autowired
    private EspacoService espacoService;

    @Autowired
    private EdicaoService edicaoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Espaco> getAllEspacos() {
        return espacoService.getAllEspacos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Espaco> getEspacoById(@PathVariable Integer id) {
        Espaco espaco = espacoService.getEspacoById(id);
        if (espaco == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(espaco);
    }

    @PostMapping
    public ResponseEntity<Espaco> createEspaco(@RequestBody Espaco espaco, @RequestParam Integer usuarioId, @RequestParam Integer edicaoId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        Edicao edicao = edicaoService.getEdicaoById(edicaoId);
        if (usuario == null || edicao == null || !usuarioService.isOrganizador(usuario, edicao)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(espacoService.saveEspaco(espaco));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Espaco> updateEspaco(@PathVariable Integer id, @RequestBody Espaco espacoDetails, @RequestParam Integer usuarioId, @RequestParam Integer edicaoId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        Edicao edicao = edicaoService.getEdicaoById(edicaoId);
        Espaco espaco = espacoService.getEspacoById(id);
        if (espaco == null || usuario == null || edicao == null || !usuarioService.isOrganizador(usuario, edicao)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        espaco.setNome(espacoDetails.getNome());
        espaco.setLocalizacao(espacoDetails.getLocalizacao());
        espaco.setCapacidade(espacoDetails.getCapacidade());
        espaco.setRecursos(espacoDetails.getRecursos());

        Espaco updatedEspaco = espacoService.saveEspaco(espaco);
        return ResponseEntity.ok(updatedEspaco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspaco(@PathVariable Integer id, @RequestParam Integer usuarioId, @RequestParam Integer edicaoId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        Edicao edicao = edicaoService.getEdicaoById(edicaoId);
        Espaco espaco = espacoService.getEspacoById(id);
        if (espaco == null || usuario == null || edicao == null || !usuarioService.isOrganizador(usuario, edicao)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        espacoService.deleteEspaco(id);
        return ResponseEntity.noContent().build();
    }
}

