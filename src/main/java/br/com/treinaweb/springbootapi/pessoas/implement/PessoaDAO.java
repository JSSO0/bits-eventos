package br.com.treinaweb.springbootapi.pessoas.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.UUID;

import br.com.treinaweb.springbootapi.pessoas.atribuicoes.PessoasDefinicoes;
import br.com.treinaweb.springbootapi.general.sqlUtil.SqlUtil;
import br.com.treinaweb.springbootapi.pessoas.entity.Pessoa;
import org.jetbrains.annotations.NotNull;


public class PessoaDAO {
    private final Connection connection;
    private final PessoasDefinicoes pessoaMapper;

    public PessoaDAO(Connection connection) throws SQLException {
        this.connection = connection;
        this.pessoaMapper = new PessoasDefinicoes();
    }

    public List<Pessoa> listarTodosOsUsuarios() throws SQLException {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Pessoa pessoa = pessoaMapper.mapResultSetToPessoa(resultSet);
                pessoas.add(pessoa);
            }
        }
        return pessoas;
    }

    public Pessoa criarUsuarioNormal(Pessoa pessoa) throws SQLException {
        String sqlInsert = "INSERT INTO Usuario (name, phone, email, is_adm, password) VALUES (?, ?, ?, ?, ?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {
            preparedStatement.setString(1, pessoa.getName());
            preparedStatement.setString(2, pessoa.getPhone());
            preparedStatement.setString(3, pessoa.getEmail());
            preparedStatement.setBoolean(4, pessoa.getAdm());
            preparedStatement.setString(5, pessoa.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao executar a inserção no banco de dados:");
            e.printStackTrace();
        }

        return pessoa;
    }


    public Pessoa checarLogin(Pessoa pessoa) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE email = ? AND password = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, pessoa.getEmail());
            preparedStatement.setString(2, pessoa.getPassword());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Pessoa pessoaLogada = new Pessoa();
                    String token = UUID.randomUUID().toString();
                    pessoaLogada.setToken(token);
                    pessoaLogada.setId(resultSet.getString("id"));
                    pessoaLogada.setName(resultSet.getString("name"));



                    return pessoaLogada;
                }
            }
        }
        return null;
    }

    public void criarUsuarioCompany(Pessoa pessoa) throws SQLException {
        String sql = "INSERT INTO Usuario ( name,  phone, email, is_adm, password, company_name) VALUES ( ?, ?, ?, false, ?,?);";
        SqlUtil.executeInsert(sql, connection, pessoa);
    }


    public Pessoa consultarPessoaPorId(String id) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE id = CAST(? AS UUID)";

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

    public void atualizarPessoaNormal(Pessoa pessoa) throws SQLException {
        String sql = "UPDATE Usuario SET name = ?, phone = ?, email = ?, is_adm = false, password = ? WHERE id = CAST(? AS UUID)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, pessoa.getName());
            preparedStatement.setString(2, pessoa.getPhone());
            preparedStatement.setString(3, pessoa.getEmail());
            preparedStatement.setString(4, pessoa.getPassword());
            preparedStatement.setString(5, pessoa.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Atualização falhou, nenhum linha afetada.");
            }
        }
    }

    public void atualizarPessoaCompany(Pessoa pessoa) throws SQLException {
        String sql = "UPDATE Usuario SET name = ?, phone = ?, email = ?, is_adm = false, password = ?, company_name = ? WHERE id = ?";

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
