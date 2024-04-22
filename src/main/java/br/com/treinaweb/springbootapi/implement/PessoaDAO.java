package br.com.treinaweb.springbootapi.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.treinaweb.springbootapi.atribuicoes.PessoasDefinicoes;
import br.com.treinaweb.springbootapi.entity.Pessoa;
import br.com.treinaweb.springbootapi.atribuicoes.SqlUtil;


public class PessoaDAO {
    private final Connection connection;
    private final PessoasDefinicoes pessoaMapper;


    // Construtor que recebe uma conexão com o banco de dados
    public PessoaDAO(Connection connection) throws SQLException {
        this.connection = connection;
        this.pessoaMapper = new PessoasDefinicoes();
    }

    public List<Pessoa> listarTodasAsPessoas() throws SQLException {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM pessoa";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Pessoa pessoa = pessoaMapper.mapResultSetToPessoa(resultSet);
                pessoas.add(pessoa);
            }
        }

        return pessoas;
    }

    public void criarPessoa(Pessoa pessoa) throws SQLException {
        String sql = "INSERT INTO pessoa (id, cpf, email, nome, telefone, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";

        SqlUtil.executeInsert(sql, connection, pessoa);

    }

    public Pessoa consultarPessoaPorId(String id) throws SQLException {
        String sql = "SELECT * FROM pessoa WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    PessoasDefinicoes pessoaMapper = new PessoasDefinicoes();
                    return pessoaMapper.mapResultSetToPessoa(resultSet);
                } else {
                    return null;
                }
            }
        }
    }

    public void atualizarPessoa(Pessoa pessoa) throws SQLException {
        String sql = "UPDATE pessoa SET nome = ?, telefone = ?, email = ?, cpf = ?, username = ?, password = ? WHERE id = ?";

        SqlUtil.executeInsert(sql, connection, pessoa);
    }

    public void excluirPessoa(String id) throws SQLException {
        String sql = "DELETE FROM pessoa WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
