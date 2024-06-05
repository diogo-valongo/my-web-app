package com.mycompany.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mycompany.app.models.Edicao;
import com.mycompany.app.services.EdicaoService;
import com.mycompany.app.models.Usuario;
import com.mycompany.app.services.UsuarioService;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/edicoes")
public class EdicaoController {

    @Autowired
    private EdicaoService edicaoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Edicao> getAllEdicoes() {
        return edicaoService.getAllEdicoes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Edicao> getEdicaoById(@PathVariable Integer id) {
        Edicao edicao = edicaoService.getEdicaoById(id);
        if (edicao == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(edicao);
    }

    @PostMapping
    public ResponseEntity<Edicao> createEdicao(@RequestBody Edicao edicao, @RequestParam Integer usuarioId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null || !usuarioService.isAdmin(usuario)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(edicaoService.saveEdicao(edicao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Edicao> updateEdicao(@PathVariable Integer id, @RequestBody Edicao edicaoDetails, @RequestParam Integer usuarioId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        Edicao edicao = edicaoService.getEdicaoById(id);
        if (edicao == null || usuario == null || !usuarioService.isAdmin(usuario) && !usuarioService.isOrganizador(usuario, edicao)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        edicao.setNumero(edicaoDetails.getNumero());
        edicao.setAno(edicaoDetails.getAno());
        edicao.setCidade(edicaoDetails.getCidade());
        edicao.setDataInicial(edicaoDetails.getDataInicial());
        edicao.setDataFinal(edicaoDetails.getDataFinal());
        edicao.setDataSubmissao(edicaoDetails.getDataSubmissao());
        edicao.setDataDivulgacao(edicaoDetails.getDataDivulgacao());
        edicao.setDataEntregaFinal(edicaoDetails.getDataEntregaFinal());
        edicao.setChamadaTrabalhos(edicaoDetails.getChamadaTrabalhos());
        edicao.setInfo_Incricao(edicaoDetails.getInfo_Incricao());
        edicao.setPrecosPorLote(edicaoDetails.getPrecosPorLote());
        edicao.setLink_Inscricao(edicaoDetails.getLink_Inscricao());

        Edicao updatedEdicao = edicaoService.saveEdicao(edicao);
        return ResponseEntity.ok(updatedEdicao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEdicao(@PathVariable Integer id, @RequestParam Integer usuarioId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if (usuario == null || !usuarioService.isAdmin(usuario)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        edicaoService.deleteEdicao(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/usuarios")
    public ResponseEntity<List<Usuario>> getUsuariosByEdicao(@PathVariable Integer id) {
        Edicao edicao = edicaoService.getEdicaoById(id);
        if (edicao == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(edicao.getUsuarios());
    }

    @PostMapping("/{id}/usuarios")
    public ResponseEntity<Void> addUsuarioToEdicao(@PathVariable Integer id, @RequestParam Integer usuarioId) {
        Edicao edicao = edicaoService.getEdicaoById(id);
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);

        if (edicao == null || usuario == null) {
            return ResponseEntity.notFound().build();
        }

        edicao.getUsuarios().add(usuario);
        edicaoService.saveEdicao(edicao);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/usuarios")
    public ResponseEntity<Void> deleteUsuarioToEdicao(@PathVariable Integer id, @RequestParam Integer usuarioId) {
        Edicao edicao = edicaoService.getEdicaoById(id);
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);

        if (edicao == null || usuario == null) {
            return ResponseEntity.notFound().build();
        }

        edicao.getUsuarios().remove(usuario);
        edicaoService.saveEdicao(edicao);
        return ResponseEntity.ok().build();
    }
}
