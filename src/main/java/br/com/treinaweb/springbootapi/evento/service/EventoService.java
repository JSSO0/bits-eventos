package br.com.treinaweb.springbootapi.evento.service;

import br.com.treinaweb.springbootapi.evento.atribuicoes.EventoDefinicoes;
import br.com.treinaweb.springbootapi.evento.entity.Evento;
import br.com.treinaweb.springbootapi.evento.implement.EventoDAO;
import jdk.jfr.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class EventoService {

    final EventoDAO eventoDAO;

    @Autowired
    public EventoService(EventoDAO eventoDAO) throws SQLException{
        this.eventoDAO =  eventoDAO;
    }
    public List<Evento> listarTodosEventos() throws SQLException{
        return  eventoDAO.listarTodosEventos();
    }
public List<Evento> listarMeusEventos(UUID userId) throws  SQLException{
        return  eventoDAO.getEventosInscritos(userId);
}
    public Evento listarEventoEspecifico(String id) throws SQLException{
        return eventoDAO.consultarEvento(id);
    }

    public Evento criarEvento(Evento evento) throws SQLException{
        eventoDAO.criarEvento(evento);
        return evento;
    }

    public Evento atualizarEvento(String id, Evento newEvento) throws SQLException {
        Evento eventoExistente = eventoDAO.consultarEvento(id);
        if(eventoExistente !=null) {
            EventoDefinicoes eventoMapper = new EventoDefinicoes();
            eventoDAO.atualizarEvento(new Evento());
            eventoMapper.copiarAtributos(eventoExistente, newEvento);
            return  eventoExistente;
        }else {
            return null;
        }
    }

    public void excluirEvento(String id) throws SQLException{
        eventoDAO.deletarEvento(id);
    }

}
