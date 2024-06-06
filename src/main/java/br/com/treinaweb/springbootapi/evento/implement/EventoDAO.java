package br.com.treinaweb.springbootapi.evento.implement;

import br.com.treinaweb.springbootapi.evento.atribuicoes.EventoDefinicoes;
import br.com.treinaweb.springbootapi.general.sqlUtil.SqlUtil;
import br.com.treinaweb.springbootapi.evento.entity.Evento;
import br.com.treinaweb.springbootapi.pessoas.entity.Pessoa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventoDAO {
    private final Connection connection;
    private final EventoDefinicoes eventoMapper;

    public static class PessoaDTO {
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        private String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;


    }
    public EventoDAO(Connection connection) throws SQLException {
        this.connection = connection;
        this.eventoMapper = new EventoDefinicoes();
    }

    public int getParticipantCount(UUID eventId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM participants WHERE event_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, eventId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        }
        return 0;
    }

    public List<Evento> getEventosInscritos(UUID userId) throws SQLException {
        List<Evento> eventos = new ArrayList<>();
        String sql = "SELECT e.* FROM evento e INNER JOIN participants p ON e.id = p.event_id WHERE p.user_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Evento evento = new Evento();
                    evento.setId(UUID.fromString(resultSet.getString("id")));
                    evento.setName(resultSet.getString("name"));
                    evento.setDescription(resultSet.getString("description"));
                    evento.setCreated_at(resultSet.getString("created_at"));
                    evento.setStarts_in(resultSet.getString("Starts_in"));
                    evento.setEnd_in(resultSet.getString("end_in"));
                    evento.setPayed_event(Boolean.valueOf(resultSet.getString("payed_event")));
                    evento.setValue_event(resultSet.getString("value_event"));
                    evento.setCompany_id(resultSet.getString("company_id"));
                    int participantCount = getParticipantCount(evento.getId());
                    evento.setParticipantCount(participantCount);
                    eventos.add(evento);
                }
            }
        }
        return eventos;
    }

    public List<PessoaDTO> listarOsUsuariosdoEvento(UUID id) throws SQLException {
        List<PessoaDTO> pessoas = new ArrayList<>();
        String sql = "SELECT u.id AS user_id, u.name\n" +
                "FROM public.participants AS p\n" +
                "JOIN public.usuario AS u ON p.user_id = u.id\n" +
                "WHERE p.event_id = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PessoaDTO pessoa = new PessoaDTO();
                pessoa.setId(String.valueOf((UUID) resultSet.getObject("user_id")));
                pessoa.setName(resultSet.getString("name"));
                pessoas.add(pessoa);
            }
        }
        return pessoas;
    }
    public List<Evento> listarTodosEventos() throws SQLException {
        List<Evento> eventos = new ArrayList<>();
        String sql = "SELECT * from evento";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Evento evento = eventoMapper.mapResultSetToEvento(resultSet);
                int participantCount = getParticipantCount(evento.getId());
                evento.setParticipantCount(participantCount);
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
    public int consultarQuantidadeUsuariosPorNome(String nome) throws SQLException {
        String sql = "SELECT COUNT(p.id) AS total_users " +
                "FROM public.evento e " +
                "JOIN public.participants p ON e.id = p.event_id " +
                "WHERE e.name = ? " +
                "GROUP BY e.id, e.name;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nome);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("total_users");
                }
            }
        }
        return 0;  // Se não houver resultado, retorna 0
    }


    public int consultarQuantidadeUsuario() throws SQLException {
        String sql = "SELECT \n" +
                "    e.name AS event_name,\n" +
                "    COUNT(p.id) AS total_users\n" +
                "FROM \n" +
                "    public.evento e\n" +
                "JOIN \n" +
                "    public.participants p ON e.id = p.event_id\n" +
                "GROUP BY \n" +
                "    e.id, e.name\n" +
                "ORDER BY \n" +
                "    total_users DESC;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("total_users");
                }
            }
        }
        return 0;
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


