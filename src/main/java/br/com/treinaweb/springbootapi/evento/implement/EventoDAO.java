package br.com.treinaweb.springbootapi.evento.implement;

import br.com.treinaweb.springbootapi.evento.atribuicoes.EventoDefinicoes;
import br.com.treinaweb.springbootapi.general.sqlUtil.SqlUtil;
import br.com.treinaweb.springbootapi.evento.entity.Evento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventoDAO {
    private final Connection connection;
    private final EventoDefinicoes eventoMapper;


    public EventoDAO(Connection connection) throws SQLException {
        this.connection = connection;
        this.eventoMapper = new EventoDefinicoes();
    }

    public List<Evento> listarTodosEventos() throws SQLException {
        List<Evento> eventos = new ArrayList<>();
        String sql = "SELECT * from evento";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {


            while (resultSet.next()) {
                Evento evento = eventoMapper.mapResultSetToEvento(resultSet);
                eventos.add(evento);
            }
        }
        return eventos;
    }

    public void criarEvento(Evento evento) throws SQLException {
        String sql = "INSERT INTO Event (id, name, description, created_at, starts_in, end_in, payed_event, value_event, company_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        SqlUtil.executeInsert(sql, connection, evento);
    }


    public Evento consultarEvento(String id) throws SQLException {
        String sql = "SELECT * FROM Event WHERE id = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    EventoDefinicoes eventomapper = new EventoDefinicoes();
                    return eventomapper.mapResultSetToEvento(resultSet);
                }
            }
        }
        return null;
    }

    public void atualizarEvento(Evento evento) throws SQLException {
        String sql = "UPDATE Event SET name = ?, description = ?, starts_in = ?, end_in = ?, " +
                "payed_event = ?, value_event = ?, company_id = ? WHERE id = ?;";
        SqlUtil.executeInsert(sql, connection, evento);
    }

    // Deleção de um evento pelo ID
    public void deletarEvento(String id) throws SQLException {
        String sql = "DELETE FROM Event WHERE id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }
    }
}


