package br.com.treinaweb.springbootapi.implement;

import br.com.treinaweb.springbootapi.atribuicoes.EventoDefinicoes;
import br.com.treinaweb.springbootapi.atribuicoes.PessoasDefinicoes;
import br.com.treinaweb.springbootapi.atribuicoes.SqlUtil;
import br.com.treinaweb.springbootapi.entity.Evento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {
    private final Connection connection;
    private final EventoDefinicoes eventoMapper;


    // Construtor que recebe uma conexão com o banco de dados
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
    public void criarEvento(Evento evento) throws SQLException{
        String sql = "INSERT INTO evento(data_evento, name_evento, evento_pago, evento_valor, adm_id) values (?,?,?,?,?)";
        SqlUtil.executeInsert(sql, connection, evento);
    }

}

