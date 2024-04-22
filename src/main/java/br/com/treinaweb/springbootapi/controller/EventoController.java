package br.com.treinaweb.springbootapi.controller;

import br.com.treinaweb.springbootapi.entity.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
@CrossOrigin(origins = "http://localhost:3000")
public class EventoController {

    @GetMapping
    public ResponseEntity<List<Evento>> ListasEventos(){
        return (ResponseEntity<List<Evento>>) ResponseEntity.status(HttpStatus.CREATED);
    }

}
