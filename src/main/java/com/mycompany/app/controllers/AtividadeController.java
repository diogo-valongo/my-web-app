package com.mycompany.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import com.mycompany.app.models.Atividade;
import com.mycompany.app.services.AtividadeService;
import java.util.List;
import com.mycompany.app.models.Usuario;
import com.mycompany.app.services.UsuarioService;
import com.mycompany.app.models.Edicao;
import com.mycompany.app.services.EdicaoService;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EdicaoService edicaoService;

    @GetMapping
    public List<Atividade> getAllAtividades() {
        return atividadeService.getAllAtividades();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atividade> getAtividadeById(@PathVariable Integer id) {
        Atividade atividade = atividadeService.getAtividadeById(id);
        if (atividade == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atividade);
    }

    @PostMapping
    public ResponseEntity<Atividade> createAtividade(@RequestBody Atividade atividade, @RequestParam Integer usuarioId, @RequestParam Integer edicaoId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        Edicao edicao = edicaoService.getEdicaoById(edicaoId);
        if (usuario == null || edicao == null || !usuarioService.isOrganizador(usuario, edicao)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        atividade.setEdicao(edicao);
        return ResponseEntity.ok(atividadeService.saveAtividade(atividade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atividade> updateAtividade(@PathVariable Integer id, @RequestBody Atividade atividadeDetails, @RequestParam Integer usuarioId, @RequestParam Integer edicaoId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        Edicao edicao = edicaoService.getEdicaoById(edicaoId);
        Atividade atividade = atividadeService.getAtividadeById(id);
        if (atividade == null || usuario == null || edicao == null || !usuarioService.isOrganizador(usuario, edicao)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        atividade.setNome(atividadeDetails.getNome());
        atividade.setTipoEnum(atividadeDetails.getTipoEnum());
        atividade.setDescricao(atividadeDetails.getDescricao());
        atividade.setData(atividadeDetails.getData());
        atividade.setHorarioInicio(atividadeDetails.getHorarioInicio());
        atividade.setHorarioFinal(atividadeDetails.getHorarioFinal());
        atividade.setLocal(atividadeDetails.getLocal());

        Atividade updatedAtividade = atividadeService.saveAtividade(atividade);
        return ResponseEntity.ok(updatedAtividade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtividade(@PathVariable Integer id, @RequestParam Integer usuarioId, @RequestParam Integer edicaoId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        Edicao edicao = edicaoService.getEdicaoById(edicaoId);
        Atividade atividade = atividadeService.getAtividadeById(id);
        if (atividade == null || usuario == null || edicao == null || !usuarioService.isOrganizador(usuario, edicao)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        atividadeService.deleteAtividade(id);
        return ResponseEntity.noContent().build();
    }
}
