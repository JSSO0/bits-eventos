package br.com.treinaweb.springbootapi.participants.implement;

import br.com.treinaweb.springbootapi.general.sqlUtil.SqlUtil;
import br.com.treinaweb.springbootapi.participants.atribuicoes.ParticipantsDefinicoes;
import br.com.treinaweb.springbootapi.participants.entity.Participantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ParticipantsDAO {

    private final Connection connection;
    private final ParticipantsDefinicoes participantesMapper;

    public ParticipantsDAO(Connection connection) throws SQLException {
        this.connection = connection;
        this.participantesMapper = new ParticipantsDefinicoes();
    }

    public List<Participantes> listarTodosParticipantes() throws SQLException {
        List<Participantes> participantes = new ArrayList<>();
        String sql = "SELECT * FROM Participants";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Participantes participante = participantesMapper.mapResultSetToParticipantes(resultSet);
                participantes.add(participante);
            }
        }
        return participantes;
    }

    public void criarParticipante(Participantes participante) throws SQLException {
        String sql = "INSERT INTO Participants (id, event_id, user_id, adm_of_event) VALUES (?, ?, ?, ?);";
        SqlUtil.executeInsert(sql, connection, participante);
    }


    public Participantes consultarParticipante(String id) throws SQLException {
        String sql = "SELECT * FROM Participants WHERE id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ParticipantsDefinicoes participantsMapper = new ParticipantsDefinicoes();
                    return participantsMapper.mapResultSetToParticipantes(rs);
                } else {
                    return null;
                }
            }

        }
    }


    public void atualizarParticipante(Participantes participante) throws SQLException {
        String sql = "UPDATE Participants SET event_id = ?, user_id = ?, adm_of_event = ? WHERE id = ?;";
        SqlUtil.executeInsert(sql, connection, participante);
    }

    public void deletarParticipante(String id) throws SQLException {
        String sql = "DELETE FROM Participants WHERE id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }
    }
}
