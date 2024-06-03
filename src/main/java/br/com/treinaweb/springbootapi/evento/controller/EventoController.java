package br.com.treinaweb.springbootapi.evento.controller;

import br.com.treinaweb.springbootapi.company.entity.Company;
import br.com.treinaweb.springbootapi.company.service.CompanyService;
import br.com.treinaweb.springbootapi.evento.entity.Evento;
import br.com.treinaweb.springbootapi.evento.service.EventoService;
import br.com.treinaweb.springbootapi.general.exceptionhandler.ExcecoesPersonalizadas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/eventos")
@CrossOrigin(origins = "http://localhost:3000")
public class EventoController {
    @Autowired
    private final EventoService eventoService;


    @Autowired
    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public ResponseEntity<List<Evento>> listarEvento() throws ExcecoesPersonalizadas.ListarExcessao, SQLException {
        List<Evento> eventos = eventoService.listarTodosEventos();
        return ResponseEntity.ok(eventos);
    }
    @GetMapping("/meus-eventos/{userId}")
    public ResponseEntity<List<Evento>> listarMeusEventos(@PathVariable UUID userId) throws ExcecoesPersonalizadas.ListarExcessao, SQLException {
        List<Evento> eventos = eventoService.listarMeusEventos(userId);
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> obterEvento(@PathVariable String id) throws ExcecoesPersonalizadas.BuscarPessoaExcessao, SQLException {
        Evento evento = eventoService.listarEventoEspecifico(id);
        if (evento != null) {
            return ResponseEntity.ok(evento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Evento> criarEvento(@RequestBody Evento evento) throws ExcecoesPersonalizadas.CriarPessoaExcessao, SQLException {
        evento = eventoService.criarEvento(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(evento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizarEvento(@PathVariable String id, @Valid @RequestBody Evento newEvento) throws ExcecoesPersonalizadas.AtualizarPessoaExcessao, SQLException {
        Evento evento = eventoService.atualizarEvento(id, newEvento);
        if (evento != null) {
            return ResponseEntity.ok(evento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirEvento(@PathVariable String id) throws ExcecoesPersonalizadas.DeletarPessoaExcessao, SQLException {
        eventoService.excluirEvento(id);
        return ResponseEntity.noContent().build();
    }

}
