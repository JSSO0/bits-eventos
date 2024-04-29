package br.com.treinaweb.springbootapi.participants.controller;

import br.com.treinaweb.springbootapi.evento.entity.Evento;
import br.com.treinaweb.springbootapi.evento.service.EventoService;
import br.com.treinaweb.springbootapi.general.exceptionhandler.ExcecoesPersonalizadas;
import br.com.treinaweb.springbootapi.participants.entity.Participantes;
import br.com.treinaweb.springbootapi.participants.service.ParticipantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/participantes")
@CrossOrigin(origins = "http://localhost:3000")
public class ParticipantesController {
    @Autowired
    private final ParticipantesService participantesService;


    @Autowired
    public ParticipantesController(ParticipantesService participantesService) {
        this.participantesService = participantesService;
    }

    @GetMapping
    public ResponseEntity<List<Participantes>> listarParticipantes() throws ExcecoesPersonalizadas.ListarExcessao, SQLException {
        List<Participantes> participantes = participantesService.listarParticipantes();
        return ResponseEntity.ok(participantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participantes> obterParticipantes(@PathVariable String id) throws ExcecoesPersonalizadas.BuscarPessoaExcessao, SQLException {
        Participantes participantes = participantesService.obterParticipantesId(id);
        if (participantes != null) {
            return ResponseEntity.ok(participantes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Participantes> criarParticipantes(@RequestBody Participantes participantes) throws ExcecoesPersonalizadas.CriarPessoaExcessao, SQLException {
        participantes = participantesService.criarParticipante(participantes);
        return ResponseEntity.status(HttpStatus.CREATED).body(participantes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participantes> atualizarParticipantes(@PathVariable String id, @Valid @RequestBody Participantes newParticipantes) throws ExcecoesPersonalizadas.AtualizarPessoaExcessao, SQLException {
        Participantes participantes = participantesService.atualizarParticipantes(id, newParticipantes);
        if (participantes != null) {
            return ResponseEntity.ok(participantes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirParticipantes(@PathVariable String id) throws ExcecoesPersonalizadas.DeletarPessoaExcessao, SQLException {
        participantesService.excluirParticipantes(id);
        return ResponseEntity.noContent().build();
    }
}
