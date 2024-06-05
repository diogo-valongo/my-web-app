package com.mycompany.app.controllers;

import com.mycompany.app.models.Evento;
import com.mycompany.app.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.mycompany.app.models.Usuario;
import com.mycompany.app.services.UsuarioService;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Evento> getAllEventos() {
        return eventoService.getAllEventos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Integer id) {
        Evento evento = eventoService.getEventoById(id);
        if (evento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(evento);
    }

    @PostMapping
    public ResponseEntity<Evento> createEvento(@RequestBody Evento evento, @RequestParam Integer usuarioId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null || !usuarioService.isAdmin(usuario)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(eventoService.saveEvento(evento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable Integer id, @RequestBody Evento eventoDetails, @RequestParam Integer usuarioId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null || !usuarioService.isAdmin(usuario)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Evento evento = eventoService.getEventoById(id);
        if (evento == null) {
            return ResponseEntity.notFound().build();
        }

        evento.setNome(eventoDetails.getNome());
        evento.setDescricao(eventoDetails.getDescricao());
        evento.setSigla(eventoDetails.getSigla());
        evento.setCaminho(eventoDetails.getCaminho());

        Evento updatedEvento = eventoService.saveEvento(evento);
        return ResponseEntity.ok(updatedEvento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Integer id,@RequestParam Integer usuarioId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null || !usuarioService.isAdmin(usuario)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Evento evento = eventoService.getEventoById(id);
        if (evento == null) {
            return ResponseEntity.notFound().build();
        }

        eventoService.deleteEvento(id);
        return ResponseEntity.noContent().build();
    }
}
