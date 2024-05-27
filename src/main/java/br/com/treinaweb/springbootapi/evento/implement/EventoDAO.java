package br.com.treinaweb.springbootapi.evento.implement;

import br.com.treinaweb.springbootapi.evento.atribuicoes.EventoDefinicoes;
import br.com.treinaweb.springbootapi.general.sqlUtil.SqlUtil;
import br.com.treinaweb.springbootapi.evento.entity.Evento;

import java.sql.*;
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
        String sql = "INSERT INTO Evento ( name, description, created_at, starts_in, end_in, payed_event, value_event, company_id) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, evento.getName());
            pstmt.setString(2, evento.getDescription());
            pstmt.setTimestamp(3, Timestamp.valueOf(evento.getCreated_at()));
            pstmt.setTimestamp(4, Timestamp.valueOf(evento.getStarts_in()));
            pstmt.setTimestamp(5, Timestamp.valueOf(evento.getEnd_in()));
            pstmt.setBoolean(6, evento.getPayed_event());
            if (evento.getValue_event() != null) {
                pstmt.setString(7, evento.getValue_event());
            } else {
                pstmt.setNull(7, Types.NUMERIC);
            }
            pstmt.setObject(8, UUID.fromString(evento.getCompany_id()));

            System.out.println("Query SQL preenchida: " + pstmt.toString());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            // Em caso de erro ao executar a inserção, imprimir uma mensagem de erro e o stack trace
            System.err.println("Erro ao executar a inserção no banco de dados:");
            e.printStackTrace();
        }
    }




    public Evento consultarEvento(String id) throws SQLException {
        String sql = "SELECT * FROM Evento WHERE id = ?;";

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
        String sql = "UPDATE Evento SET name = ?, description = ?, starts_in = ?, end_in = ?, " +
                "payed_event = ?, value_event = ?, company_id = ? WHERE id = ?;";
        SqlUtil.executeInsert(sql, connection, evento);
    }

    // Deleção de um evento pelo ID
    public void deletarEvento(String id) throws SQLException {
        String sql = "DELETE FROM Evento WHERE id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }
    }
}


